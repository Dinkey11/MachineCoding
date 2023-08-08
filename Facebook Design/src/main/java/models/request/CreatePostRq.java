package models.request;

public class CreatePostRq {

    private String postContent;
    private Integer userId;

    public CreatePostRq(String postContent, Integer userId) {
        this.postContent = postContent;
        this.userId = userId;
    }

    public CreatePostRq() {
    }

    public String getPostContent() {
        return postContent;
    }

    public void setPostContent(String postContent) {
        this.postContent = postContent;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
