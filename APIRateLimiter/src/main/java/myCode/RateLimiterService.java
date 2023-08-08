package myCode;

public class RateLimiterService {

    public static void main(String[] args) {
        int limit = 5;
        RateLimit rateLimit = new RateLimit(5);
        new RateLimitHelper("A", rateLimit).start();
        new RateLimitHelper("B", rateLimit).start();
    }

}