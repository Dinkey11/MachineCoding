package service;

import models.Post;
import models.User;

import java.util.HashMap;

public class PostService {

    public static PostService postService = null;
    private UserService userService;

    public static PostService getInstance(){
        if(postService == null){
            postService = new PostService();
        }
        return postService;
    }

    public PostService(){
        userService = UserService.getInstance();
    }

    //Add Post
    public void addPost(Integer userId, Integer postId){
        System.out.println("Adding Post ="+ postId + " from user - " + userId );
        User user = userService.getUser(userId);
        if(user == null) return;
        Post post = new Post(postId);
        Post head = user.getHead();
        Post mostRecent = head.getNext();
        head.setNext(post);
        post.setNext(mostRecent);
        mostRecent.setPrev(post);
        post.setPrev(head);
        HashMap<Integer, Post> postMap = user.getPosts();
        postMap.put(postId, post);
        return;
    }

    //Delete Post
    public void deletePost(Integer userId, Integer postId){
        System.out.println("Deleting Post ="+ postId + " from user - " + userId );
        User user = userService.getUser(userId);
        if(user == null) return;
        HashMap<Integer, Post> postMap = user.getPosts();
        postMap.remove(postId);
        Post head = user.getHead();
        Post curr = head;
        Post prev = null;
        while(curr.getId() != postId){
            prev = curr;
            curr = curr.getNext();
        }

        prev.setNext(curr.getNext());
        curr.getNext().setPrev(prev);
        return;
    }

    //getPost
    public Post getPost(Integer userId, Integer postId){
        User user = userService.getUser(userId);
        if(user == null) return null;
        HashMap<Integer, Post> postMap = user.getPosts();
        //Return most recent post
        if(postId == null){
            Post head = user.getHead();
            if(head.getNext().getId() != 10000)
                return head.getNext();
            else
                return null;
        }
        //Return the post with given Id
        return postMap.getOrDefault(postId, null);
    }

}
