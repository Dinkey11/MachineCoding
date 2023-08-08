package models.request;

public class AddFriendsRq {

    private Integer sender;
    private Integer receiver;

    public AddFriendsRq(Integer sender, Integer receiver) {
        this.sender = sender;
        this.receiver = receiver;
    }

    public AddFriendsRq() {
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
}
