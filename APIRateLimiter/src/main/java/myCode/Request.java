package myCode;


import java.time.Instant;

public class Request {

    private Integer count;
    private Instant timestamp;

    public Request(Integer count, Instant timestamp) {
        this.count = count;
        this.timestamp = timestamp;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }
}