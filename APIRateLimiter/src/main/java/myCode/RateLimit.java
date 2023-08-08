package myCode;

import java.time.Duration;
import java.time.Instant;
import java.util.LinkedList;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Implementation of Sliding window algorithm with timestamp and counter
 * (example: redis hash)
 */
public class RateLimit {

    private Integer rateLimit;

    public RateLimit(Integer rateLimit) {
        this.rateLimit = rateLimit;
    }

    private ConcurrentHashMap<String, LinkedList<Request>> requestMap = new ConcurrentHashMap<>();

    public synchronized  boolean hitAPI(String user, Instant timestamp){
        LinkedList<Request> exisitng = requestMap.getOrDefault(user, new LinkedList<>());
        Request newRq = new Request(1, timestamp);
        if(getTotalElapsedRequest(exisitng) < rateLimit){
            exisitng.add(newRq);
            requestMap.put(user, exisitng);
            return true;
        }else{
            Boolean removed = false;
            for(int i=0;i<exisitng.size();i++){
                Duration duration = Duration.between(exisitng.get(i).getTimestamp(), timestamp);
                if(duration.getSeconds() > 10){
                    exisitng.remove(i);
                    removed = true;
                }else {
                    break;
                }
            }
            if(removed){
                exisitng.add(newRq);
                requestMap.put(user, exisitng);
                return true;
            }
            return false;
        }
    }

    private int getTotalElapsedRequest(LinkedList<Request> existing){
        return existing.size();
    }

}