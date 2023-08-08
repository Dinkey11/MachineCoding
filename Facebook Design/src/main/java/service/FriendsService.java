package service;

import models.friends.Status;
import repository.FriendsRepository;

import java.util.List;
import java.util.stream.Collectors;

public class FriendsService {

    public static FriendsService friendsService = null;

    private FriendsRepository friendsRepository;
    private UserService userService;

    public static FriendsService getInstance(){
        if(friendsService == null){
            friendsService = new FriendsService();
            return friendsService;
        }
        return friendsService;
    }

    public FriendsService() {
        friendsRepository = FriendsRepository.getInstance();
        userService = UserService.getInstance();
    }

    public void createFriendRequest(Integer user1, Integer user2){
        friendsRepository.addFriendRequest(user1, user2);
        return;
    }

    public void updateFriendRequest(Integer user1, Integer user2, Status status){
        friendsRepository.updateFriendRequest(user1, user2, status);
    }

    public Status getFriendRequestStatus(Integer user1, Integer user2){
        return friendsRepository.getReqStatus(user1, user2);
    }

    public List<String> getFriends(Integer userid){
        List<Integer> userIDs = friendsRepository.getFriends(userid);
        List<String> friends = userIDs.stream().map(user -> userService.getUser(user).getFirstName()).collect(Collectors.toList());
        return  friends;
    }

    public void removeFriend(Integer u1, Integer u2){
        friendsRepository.removeFriend(u1, u2);
    }
}
