package service;

import models.posts.Post;
import repository.PostRepository;

import java.time.LocalDate;

public class PostService {

    public static PostService postService = null;
    private PostRepository postRepository;

    public static PostService getInstance(){
        if(postService == null){
            postService = new PostService();
        }
        return postService;
    }

    public PostService(){
        postRepository = PostRepository.getInstance();
    }

    public void addPost(String userId, String postContent){
        Post post = new Post();
        post.setUserId(userId);
        post.setPostContent(postContent);
        post.setCreatedDate(LocalDate.now());
        try{
            postRepository.addPost(post);
        }catch (Exception ex){
            System.out.println("Could not add user with error =" + ex.getMessage());
        }
    }
}
