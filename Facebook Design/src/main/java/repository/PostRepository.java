package repository;

import exceptions.AddPostException;
import models.posts.Post;

import java.util.HashMap;

public class PostRepository {

    public static PostRepository postRepository = null;

    private HashMap<Integer, Post> postMap;

    public static PostRepository getInstance(){
        if(postRepository == null){
            return postRepository = new PostRepository();
        }
        return postRepository;
    }

    public PostRepository() {
        postMap = new HashMap<>();
    }

    public void addPost(Post post) throws AddPostException {
        if(post.getId() == null){
            throw new AddPostException("Post Id cannot be null");
        }
        postMap.put(post.getId(), post);
        return;
    }

    public Post getPost(String id){
        Post post = postMap.getOrDefault(id, null);
        return post;
    }
}
