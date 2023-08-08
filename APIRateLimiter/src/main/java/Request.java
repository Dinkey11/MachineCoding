import java.time.Instant;

public class Request {

    private Instant timestamp;
    private Integer count;

    public Request(Instant timestamp, Integer count) {
        this.timestamp = timestamp;
        this.count = count;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}