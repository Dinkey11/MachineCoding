package models.friends;

import java.time.LocalDate;
import java.util.concurrent.atomic.AtomicInteger;

public class FriendRequest {

    private static AtomicInteger ID_GENERATOR = new AtomicInteger(1);

    private Integer id;
    private Integer sender;
    private Integer receiver;
    private Status status;
    private LocalDate sendDate;
    private LocalDate acceptedDate;

    public FriendRequest() {
        this.id = ID_GENERATOR.getAndIncrement();
    }

    public Integer getId() {
        return id;
    }

    public Integer getSender() {
        return sender;
    }

    public void setSender(Integer sender) {
        this.sender = sender;
    }

    public Integer getReceiver() {
        return receiver;
    }

    public void setReceiver(Integer receiver) {
        this.receiver = receiver;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public LocalDate getSendDate() {
        return sendDate;
    }

    public void setSendDate(LocalDate sendDate) {
        this.sendDate = sendDate;
    }

    public LocalDate getAcceptedDate() {
        return acceptedDate;
    }

    public void setAcceptedDate(LocalDate acceptedDate) {
        this.acceptedDate = acceptedDate;
    }
}
