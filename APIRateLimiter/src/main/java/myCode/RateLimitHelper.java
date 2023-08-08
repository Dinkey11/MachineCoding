package myCode;

import java.time.Instant;

public class RateLimitHelper extends Thread {

    private RateLimit rateLimit;

    public RateLimitHelper(String user, RateLimit rateLimitService) {
        super(user);
        this.rateLimit = rateLimitService;
    }

    @Override
    public void run(){
        for(int i=0;i<100;i++){
            System.out.println("Thread Name - " + getName() + ", Time - " + i + ", rate limit: " + hit( getName(), Instant.now()));
            try{
                Thread.sleep(1000);
            }catch (InterruptedException ex){

            }
        }
    }

    public boolean hit(String user, Instant ts) {
        return rateLimit.hitAPI(user, ts);
    }
}