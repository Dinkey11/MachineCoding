package models.posts;

import java.time.LocalDate;
import java.util.concurrent.atomic.AtomicInteger;

public class Post {

    private static AtomicInteger ID_GENERATOR = new AtomicInteger(1);

    private Integer id;
    private String postContent;
    private String userId;
    private LocalDate createdDate;

    public Post() {
        this.id = ID_GENERATOR.getAndIncrement();
    }

    public Integer getId() {
        return id;
    }

    public String getPostContent() {
        return postContent;
    }

    public void setPostContent(String postContent) {
        this.postContent = postContent;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }
}
