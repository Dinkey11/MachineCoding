public class RateLimiterService {

    public static void main(String[] args) {
        RateLimiterService rateLimiterService = new RateLimiterService();
        int limit = 5; // 5 requests per minute
        RateLimit rateLimit = new RateLimit(limit);
        new RateLimitHelper("UserA", rateLimit).start();
        new RateLimitHelper("userB", rateLimit).start();
    }

}