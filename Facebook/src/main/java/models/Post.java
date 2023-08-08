package models;

import java.util.concurrent.atomic.AtomicInteger;

public class Post {

    private static AtomicInteger ID_GENERATOR = new AtomicInteger(1);

    private Integer id;
    private Integer key;
    private Post next;
    private Post prev;
    private String content;

    public Post(Integer id) {
        this.id = id;
        this.key = ID_GENERATOR.getAndIncrement();
    }

    public Integer getId() {
        return id;
    }

    public Post getNext() {
        return next;
    }

    public void setNext(Post next) {
        this.next = next;
    }

    public Post getPrev() {
        return prev;
    }

    public void setPrev(Post prev) {
        this.prev = prev;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getKey() {
        return key;
    }

    public void setKey(Integer key) {
        this.key = key;
    }
}
