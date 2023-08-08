import models.Post;
import service.FeedService;
import service.PostService;
import service.UserService;

import java.time.LocalDate;
import java.util.List;

public class FBDriverClass {

    public static void main(String[] args) {

        UserService userService = UserService.getInstance();
        PostService postService = PostService.getInstance();
        FeedService feedService = FeedService.getInstance();

        /*1*/userService.createUser("Dinkey", "Mahawar", LocalDate.of(1998, 2, 11));
        /*2*/userService.createUser("Rashi", "Mahawar", LocalDate.of(1998, 2, 11));
        /*3*/userService.createUser("Shruti", "Mahawar", LocalDate.of(1998, 2, 11));
        /*4*/userService.createUser("Shubh", "Mahawar", LocalDate.of(1998, 2, 11));
        /*5*/userService.createUser("Bhavya", "Gupta", LocalDate.of(1998, 2, 11));
        /*6*/userService.createUser("Knaha", "Goyal", LocalDate.of(1998, 2, 11));
        /*7*/userService.createUser("Chaitnaya", "Sharma", LocalDate.of(1998, 2, 11));
        /*8*/userService.createUser("Vishnu", "UK", LocalDate.of(1998, 2, 11));
        /*9*/userService.createUser("Tanisha", "Yadav", LocalDate.of(1998, 2, 11));

        userService.addFollower(1, 2);
        userService.addFollower(1, 3);
        userService.addFollower(1, 8);
        userService.addFollower(2, 3);
        userService.addFollower(2, 4);
        userService.addFollower(2, 1);
        userService.addFollower(2, 5);
        userService.addFollower(4, 7);
        userService.addFollower(4, 6);
        userService.addFollower(1, 4);
        userService.addFollower(3, 7);
        userService.addFollower(5, 6);

        userService.getUser(1).printFollowers();
        userService.removeFollower(1, 2);
        userService.getUser(1).printFollowers();

        userService.getUser(5).printFollowers();
        userService.removeFollower(5, 6);
        userService.getUser(5).printFollowers();

        userService.getUser(2).printFollowers();
        userService.removeFollower(2, 4);
        userService.getUser(2).printFollowers();

        postService.addPost(1, 1);
        postService.addPost(1, 2);
        postService.addPost(1, 12);
        postService.addPost(1, 13);
        postService.addPost(1, 16);
        postService.addPost(1, 17);
        postService.addPost(2, 5);
        postService.addPost(2, 1);
        postService.addPost(2, 4);
        postService.addPost(2, 3);
        postService.addPost(2, 8);
        postService.addPost(2, 7);
        postService.addPost(3, 9);
        postService.addPost(4, 10);
        postService.addPost(1, 14);
        postService.addPost(1, 15);
        postService.addPost(4, 11);
        postService.addPost(1, 3);
        postService.addPost(1, 4);

        userService.getUser(2).printPosts();
        postService.deletePost(2, 7);
        userService.getUser(2).printPosts();


        List<Post> feed = feedService.getNewsFeed(2, 4);
        feedService.printFeed(feed, 2);

        feed = feedService.getFeedPaginated(2, 2);
        feedService.printFeed(feed, 2);

        feed = feedService.getFeedPaginated(2, 1);
        feedService.printFeed(feed, 2);

        feed = feedService.getFeedPaginated(2, 4);
        feedService.printFeed(feed, 2);

    }
}
