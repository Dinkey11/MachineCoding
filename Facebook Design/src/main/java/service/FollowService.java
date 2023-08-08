package service;

import models.user.User;
import repository.FollowRepository;

import java.util.List;
import java.util.stream.Collectors;

public class FollowService {

    public static FollowService followService = null;

    private FollowRepository followRepository;
    private UserService userService;

    public static FollowService getInstance(){
        if(followService == null){
            followService = new FollowService();
            return followService;
        }
        return followService;
    }

    public FollowService() {
        followRepository = FollowRepository.getInstance();
        userService = UserService.getInstance();
    }

    public void addFollower(Integer user1, Integer user2){
        followRepository.addFollower(user1, user2);
        return;
    }

    public List<User> getFollower(Integer user1){
        List<Integer> userIds = followRepository.getFollowers(user1);
        List<User> users = userIds.stream().map(userId -> userService.getUser(userId)).collect(Collectors.toList());
        return users;
    }

    public void removeFollow(Integer u1, Integer u2){
        followRepository.removeFollower(u1, u2);
    }

}
