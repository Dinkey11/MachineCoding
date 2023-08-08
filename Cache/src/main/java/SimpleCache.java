import java.time.Duration;
import java.time.Instant;
import java.time.temporal.TemporalUnit;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * Simple in-memory cache with LRU eviction policy. All operations on this cache are thread-safe.
 * @param <K> the key type
 * @param <V> the value type
 */
public final class SimpleCache<K, V> {
    private final Map<K, V> cacheMap;
    private final Function<K, V> valueLoader;
    private final ReentrantReadWriteLock rwl;

    // Holds the map keys using the given life time for expiration.
    private final DelayQueue<DelayedKey<K>> delayQueue;
    private final Map<K, DelayedKey<K>> expiringKeys;
    // The default max life time in milliseconds.
    private final long defaultExpiryAfter;
    private final TemporalUnit defaultExpiryUnit;

    private SimpleCache(Map<K, V> cacheMap, Function<K, V> valueLoader,
                        long defaultExpiryAfter, TemporalUnit defaultExpiryUnit) {
        this.cacheMap = cacheMap;
        this.valueLoader = valueLoader;
        this.rwl = new ReentrantReadWriteLock();
        this.delayQueue = new DelayQueue<>();
        this.expiringKeys = new HashMap<>();
        this.defaultExpiryAfter = defaultExpiryAfter;
        this.defaultExpiryUnit = defaultExpiryUnit;
    }

    /**
     * Put the given key and value into cache.
     *
     * @param key   the key
     * @param value the value
     * @return a previously associated value, if it was present; can be {@code null}
     */
    public V put(K key, V value) {
        Objects.requireNonNull(key);
        rwl.writeLock().lock();
        try {
            doCleanup();
            return internalPutValue(key, value);
        } finally {
            rwl.writeLock().unlock();
        }
    }

    /**
     * Put the given key and value into cache if the key is not already present.
     *
     * @param key   the key
     * @param value the value
     * @return the existing value if present or the given value; can be {@code null}
     */
    public V putIfAbsent(K key, V value) {
        return putIfAbsent(key, () -> value);
    }

    /**
     * Put the key into cache with the value provided by the given supplier, if key is not already present.
     *
     * @param key           the key
     * @param valueSupplier the value supplier
     * @return the existing value if present or the given value by supplier; can be {@code null}
     */
    public V putIfAbsent(K key, Supplier<V> valueSupplier) {
        Objects.requireNonNull(key);
        rwl.readLock().lock();
        try {
            doCleanup();
            V value = cacheMap.get(key);
            if (value == null && valueSupplier != null) { // cache miss
                rwl.readLock().unlock();// Must release read lock before acquiring write lock
                rwl.writeLock().lock();
                try {// recheck state because another thread might have
                    // acquired write lock and changed state before we did.
                    value = cacheMap.get(key);
                    if (value == null) { // not present in the cache
                        value = valueSupplier.get();
                        internalPutValue(key, value);
                    }
                } finally { // downgrade by acquiring read lock before releasing write lock
                    rwl.readLock().lock();
                    rwl.writeLock().unlock(); // unlock write, still hold read
                }
            }
            return value;
        } finally {
            rwl.readLock().unlock();
        }
    }

    /**
     * Get the value in the cache if present for the given key.
     *
     * @param key the key
     * @return the value if present; can be {@code null}
     */
    public V getIfPresent(K key) {
        return doGetValue(key, false);
    }

    /**
     * Get the value in the cache for the given key. If the value is {@code null}, it tries to load it from
     * the @{code valueLoader} if configured.
     *
     * @param key the key
     * @return the value from the cache or from value loader
     */
    public V get(K key) {
        return doGetValue(key, true);
    }

    private V doGetValue(K key, boolean loadIfAbsent) {
        Objects.requireNonNull(key);
        rwl.readLock().lock();
        try {
            doCleanup();
            V value = cacheMap.get(key);
            if (value == null && loadIfAbsent && valueLoader != null) { // cache miss
                rwl.readLock().unlock();// must release read lock before acquiring write lock
                rwl.writeLock().lock();
                try {// recheck state because another thread might have
                    // acquired write lock and changed state before we did.
                    value = cacheMap.get(key);
                    if (value == null) { // not present in the cache
                        value = valueLoader.apply(key);
                        internalPutValue(key, value);
                    }
                } finally { // downgrade by acquiring read lock before releasing write lock
                    rwl.readLock().lock();
                    rwl.writeLock().unlock(); // unlock write, still hold read
                }
            } else {
                renewKey(key);
            }
            return value;
        } finally {
            rwl.readLock().unlock();
        }
    }

    private V internalPutValue(K key, V value) {
        if (defaultExpiryAfter > 0) {
            DelayedKey<K> delayedKey = new DelayedKey<>(key, defaultExpiryAfter, defaultExpiryUnit);
            DelayedKey<K> oldKey = expiringKeys.put(key, delayedKey);
            if (oldKey != null) {
                delayQueue.remove(oldKey);
            }
            delayQueue.offer(delayedKey);
        }
        return cacheMap.put(key, value);
    }

    /**
     * Remove the key and value from the cache, if present.
     *
     * @param key the key
     * @return the removed value if present; can be {@code null}
     */
    public V remove(K key) {
        Objects.requireNonNull(key);
        rwl.writeLock().lock();
        try {
            doCleanup();
            delayQueue.remove(new DelayedKey<>(key));
            expiringKeys.remove(key);
            return cacheMap.remove(key);
        } finally {
            rwl.writeLock().unlock();
        }
    }

    /**
     * Clear all key-value entries from this cache.
     */
    public void clear() {
        rwl.writeLock().lock();
        try {
            cacheMap.clear();
            delayQueue.clear();
            expiringKeys.clear();
        } finally {
            rwl.writeLock().unlock();
        }
    }

    /**
     * Get the number of keys in this cache.
     *
     * @return the size of this cache
     */
    public long size() {
        cleanup();
        return cacheMap.size();
    }

    /**
     * Renews the specified key, by setting the life time to the initial value.
     *
     * @param key the key
     * @return {@code true} if the key can be renewed, {@code false} otherwise
     */
    public boolean renewKey(K key) {
        DelayedKey<K> delayedKey = expiringKeys.get(key);
        if (delayedKey != null) {
            delayedKey.renew();
            return true;
        }
        return false;
    }

    /**
     * Cleanup any expired keys.
     */
    public void cleanup() {
        rwl.writeLock().lock();
        try {
            doCleanup();
        } finally {
            rwl.writeLock().unlock();
        }
    }

    private void doCleanup() {
        DelayedKey<K> delayedKey = delayQueue.poll();
        while (delayedKey != null) {
            cacheMap.remove(delayedKey.getKey());
            expiringKeys.remove(delayedKey.getKey());
            delayedKey = delayQueue.poll();
        }
    }

    /**
     * Create a new cache builder.
     *
     * @param <K> the key type
     * @param <V> the value type
     * @return a new instance of cache builder
     */
    public static <K, V> CacheBuilder<K, V> builder() {
        return new CacheBuilder<>();
    }

    /**
     * A simple cache builder which allows easier configuration.
     */
    public static final class CacheBuilder<K, V> {
        private int initialCapacity = -1;
        private long maximumSize = -1;
        private long defaultExpiryAfter = 0;
        private TemporalUnit defaultExpiryUnit;

        /**
         * Sets the minimum total size for the internal hash tables.
         *
         * @param initialCapacity the initial capacity
         * @return {@code this} instance to support method chaining
         * @throws IllegalArgumentException if {@code initialCapacity} is negative
         */
        public CacheBuilder<K, V> initialCapacity(int initialCapacity) throws IllegalArgumentException {
            if (initialCapacity < 0) {
                throw new IllegalArgumentException("initialCapacity should be >= 0");
            }
            this.initialCapacity = initialCapacity;
            return this;
        }

        /**
         * Sets the maximum total size for the internal hash tables.
         *
         * @param maximumSize the maximum size
         * @return {@code this} instance to support method chaining
         * @throws IllegalArgumentException if {@code maximumSize} is zero or negative
         */
        public CacheBuilder<K, V> maximumSize(long maximumSize) throws IllegalArgumentException {
            if (maximumSize <= 0) {
                throw new IllegalArgumentException("maximumSize should be greater than zero");
            }
            this.maximumSize = maximumSize;
            return this;
        }

        /**
         * Sets the default time-to-live, in the given unit, for all keys in this cache.
         *
         * @param expiryAfter the amount of time to live
         * @param expiryUnit  the temporal unit of the expiry amount
         * @return {@code this} instance to support method chaining
         * @throws IllegalArgumentException if {@code expiryAfter} is zero or negative
         */
        public CacheBuilder<K, V> expireAfter(long expiryAfter, TemporalUnit expiryUnit) throws IllegalArgumentException {
            if (expiryAfter <= 0) {
                throw new IllegalArgumentException("value for expiryAfter should be greater than zero");
            }
            this.defaultExpiryAfter = expiryAfter;
            this.defaultExpiryUnit = Objects.requireNonNull(expiryUnit);
            return this;
        }

        /**
         * Build a new instance of the {@link SimpleCache} with all configured parameters.
         *
         * @param <K1> the key type
         * @param <V1> the value type
         * @return a new instance of the cache
         */
        public <K1 extends K, V1 extends V> SimpleCache<K1, V1> build() {
            return build(null);
        }

        /**
         * Build a new instance of the {@link SimpleCache} with all configured parameters and given value loader.
         *
         * @param valueLoader the value loader
         * @param <K1>        the key type
         * @param <V1>        the value type
         * @return a new instance of the cache
         */
        public <K1 extends K, V1 extends V> SimpleCache<K1, V1> build(Function<K1, V1> valueLoader) {
            initialCapacity = Math.max(initialCapacity, 0);
            Map<K1, V1> cacheMap;
            if (maximumSize > 0) {
                // LinkedHashMap as LRU map which uses access order instead of insertion order
                cacheMap = new LinkedHashMap<>(initialCapacity, 0.75f, true) {
                    @Override
                    protected boolean removeEldestEntry(Map.Entry<K1, V1> eldest) {
                        // remove the eldest entry when map size exceeds the maximum allowed limit
                        return size() > maximumSize;
                    }
                };
            } else {
                cacheMap = new HashMap<>(initialCapacity);
            }
            return new SimpleCache<>(cacheMap, valueLoader, defaultExpiryAfter, defaultExpiryUnit);
        }
    }

    private static class DelayedKey<K> implements Delayed {
        private final K key;
        private long expireAfter;
        private TemporalUnit expiryUnit;
        private Instant startTime;

        public DelayedKey(K key) {
            this.key = key;
        }

        public DelayedKey(K key, long expireAfter, TemporalUnit expiryUnit) {
            this(key);
            this.expiryUnit = expiryUnit;
            this.startTime = Instant.now();
            this.expireAfter = expireAfter;
        }

        public K getKey() {
            return key;
        }

        public void renew() {
            this.startTime = Instant.now();
        }

        @Override
        public long getDelay(TimeUnit timeUnit) {
            long diff = startTime == null ? 0 : Duration.between(Instant.now(),
                    startTime.plus(expireAfter, expiryUnit)).toMillis();
            return timeUnit.convert(diff, TimeUnit.MILLISECONDS);
        }

        @Override
        public int compareTo(Delayed that) {
            return Long.compare(this.getDelay(TimeUnit.NANOSECONDS), that.getDelay(TimeUnit.NANOSECONDS));
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            } else if (o == null || getClass() != o.getClass()) {
                return false;
            }
            return key.equals(((DelayedKey<?>) o).key);
        }

        @Override
        public int hashCode() {
            return Objects.hash(key);
        }
    }
}