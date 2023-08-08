import java.time.Duration;
import java.time.Instant;
import java.util.LinkedList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Implementation of Sliding window algorithm with timestamp and counter
 * (example: redis hash)
 */
public class RateLimit {

    int rateLimit;
    Map<String, LinkedList<Request>> userRequestMap = new ConcurrentHashMap<>();

    public RateLimit(int limit) {
        this.rateLimit = limit;
    }

    /**
     * Thread safe block being invoked by multiple threads
     * @param user username
     * @param timestamp timestamp of request
     * @return request allowed true/false
     */
    public synchronized boolean hit(String user, Instant timestamp) {

        if (!userRequestMap.containsKey(user)) {
            return addNewUser(user);
        } else {
            if (getTotalElpasedRequests(user) < rateLimit) {
                LinkedList<Request> requests = userRequestMap.get(user);
                requests.add(new Request(timestamp, 1));
                userRequestMap.put(user, requests);
                return true;
            } else {
                boolean actionTaken = false;
                for (int i = 0; i < userRequestMap.get(user).size(); i++) {
                    Duration duration = Duration.between(userRequestMap.get(user).get(i).getTimestamp(), timestamp);
                    // check for elapsed time greater than 1 minute (60 seconds)
                    // This can be passed as an argument at runtime to avoid hardcoding
                    if (duration.getSeconds() >= 60) {
                        userRequestMap.get(user).remove(i);
                        actionTaken = true;
                    } else {
                        break;
                    }
                }

                if (actionTaken) {
                    LinkedList<Request> requests = userRequestMap.get(user);
                    requests.add(new Request(timestamp, 1));
                    userRequestMap.put(user, requests);
                    return true;
                }

                return false;
            }
        }
    }

    public boolean addNewUser(String user) {
        LinkedList<Request> requests = new LinkedList<>();
        requests.add(new Request(Instant.now(), 1));
        userRequestMap.put(user, requests);
        System.out.println("New User added !! " + user);
        return true;
    }

    public Integer getTotalElpasedRequests(String user) {
        return userRequestMap.get(user).stream().mapToInt(Request::getCount).sum();
    }

}