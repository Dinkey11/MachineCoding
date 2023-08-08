import models.friends.Status;
import models.request.AddUserRq;
import models.user.Gender;
import models.user.User;
import service.FollowService;
import service.FriendsService;
import service.PostService;
import service.UserService;

import java.time.LocalDate;
import java.util.List;

public class DriverClass {

    public static void main(String args[]){
        UserService userService = UserService.getInstance();
        PostService postService = PostService.getInstance();
        FriendsService friendsService = FriendsService.getInstance();
        FollowService followService = FollowService.getInstance();

        userService.addUser(new AddUserRq("sbcvhd", "sndcj", LocalDate.of(1998, 11, 02), null, Gender.FEMALE, "253645656", null ));
        userService.addUser(new AddUserRq("Dinkey", "sndcj", LocalDate.of(1998, 11, 02), null, Gender.FEMALE, "253645656", null ));
        userService.addUser(new AddUserRq("Rashi", "sndcj", LocalDate.of(1998, 11, 02), null, Gender.FEMALE, "253645656", null ));
        userService.addUser(new AddUserRq("Shruti", "sndcj", LocalDate.of(1998, 11, 02), null, Gender.FEMALE, "253645656", null ));
        userService.addUser(new AddUserRq("Bunty", "sndcj", LocalDate.of(1998, 11, 02), null, Gender.FEMALE, "253645656", null ));
        userService.addUser(new AddUserRq("Sajal", "sndcj", LocalDate.of(1998, 11, 02), null, Gender.FEMALE, "253645656", null ));
        userService.addUser(new AddUserRq("Kanha", "sndcj", LocalDate.of(1998, 11, 02), null, Gender.FEMALE, "253645656", null ));
        userService.addUser(new AddUserRq("Chai", "sndcj", LocalDate.of(1998, 11, 02), null, Gender.FEMALE, "253645656", null ));

        friendsService.createFriendRequest(1, 2);
        friendsService.createFriendRequest(1, 3);
        friendsService.createFriendRequest(1, 5);
        friendsService.createFriendRequest(2, 3);
        friendsService.createFriendRequest(2, 4);
        friendsService.createFriendRequest(4, 5);

        friendsService.updateFriendRequest(1, 2, Status.ACCEPTED);
        friendsService.updateFriendRequest(2, 3, Status.REJECTED);
        friendsService.updateFriendRequest(1, 5, Status.ACCEPTED);
        friendsService.updateFriendRequest(2, 3, Status.ACCEPTED);
        friendsService.updateFriendRequest(2, 4, Status.REJECTED);


        followService.addFollower(1, 2);
        followService.addFollower(1, 5);
        followService.addFollower(3, 2);
        followService.addFollower(4, 3);

        User user = userService.getUser(1);

        List<User> followers = followService.getFollower(1);

        List<String> friends = friendsService.getFriends(1);
        friendsService.removeFriend(1, 2);
        List<String> friendsUpdated = friendsService.getFriends(1);

        List<User> followers4 = followService.getFollower(4);

        friendsService.getFriendRequestStatus(3, 1);
        followService.removeFollow(1, 2);
        List<User> followersAfterRemovjng = followService.getFollower(1);

    }
}
