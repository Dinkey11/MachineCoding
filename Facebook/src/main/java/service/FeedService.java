package service;

import models.Post;
import models.User;

import java.util.*;
import java.util.stream.Collectors;

public class FeedService {

    public static FeedService feedService = null;
    private UserService userService;

    public static Integer DEFAULT_FEED_SIZE = Integer.MAX_VALUE;
    public static Integer DEFAULT_PAGE_SIZE = 3;

    public static FeedService getInstance(){
        if(feedService == null){
            feedService = new FeedService();
        }
        return feedService;
    }

    public FeedService(){
        userService = UserService.getInstance();
    }


    //CreateFeed
    public List<Post> getNewsFeed(Integer userId, Integer topNPosts){
        if(topNPosts == null)
            topNPosts = DEFAULT_FEED_SIZE;

        User user = userService.getUser(userId);
        Set<Integer> followers = user.getFollowers();

        PriorityQueue<Post> pq = new PriorityQueue<>((k, v) -> (v.getKey() - k.getKey()));
        for(Integer followerId : followers){
            User follower = userService.getUser(followerId);
            Post firstPost = follower.getHead().getNext();
            if(firstPost.getId() == follower.getTail().getId()) continue;
            pq.add(firstPost);
        }

        List<Post> feed = new ArrayList<>();
        Integer n = 0;
        while(!pq.isEmpty() && n < topNPosts){
            Post curr = pq.poll();
            n++;
            feed.add(curr);
            if(curr.getNext().getId() != 10000){
                pq.add(curr.getNext());
            }
        }
        return feed;
    }
    
    //CreateFeedPaginated
    public List<Post> getFeedPaginated(Integer userId, Integer pageNumber){
        List<Post> allPosts = getNewsFeed(userId, null);
        int start = (pageNumber-1)*DEFAULT_PAGE_SIZE;
        int end = Math.min(start + DEFAULT_PAGE_SIZE, allPosts.size());
        if(start > end) return Collections.EMPTY_LIST;
        return allPosts.subList(start, end);
    }

    public void printFeed(List<Post> posts, Integer userId){
        List<Integer> postIds = posts.stream().map(p -> p.getId()).collect(Collectors.toList());
        String ans = "User - "+ userId +"  has feed -> " +
                postIds +
                '}';
        System.out.println(ans);
    }
    
}
