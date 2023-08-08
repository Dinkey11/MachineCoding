package repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class FollowRepository {

    public static FollowRepository followRepository = null;

    private HashMap<Integer, List<Integer>> followMap;

    public static FollowRepository getInstance(){
        if(followRepository == null){
            followRepository = new FollowRepository();
            return followRepository;
        }
        return followRepository;
    }

    public FollowRepository() {
        followMap = new HashMap<>();
    }

    public void addFollower(Integer user1, Integer user2){
        List<Integer> followers = followMap.getOrDefault(user1, new ArrayList<>());
        followers.add(user2);
        followMap.put(user1, followers);
        return;
    }

    public List<Integer> getFollowers(Integer userId){
        return followMap.getOrDefault(userId, Collections.EMPTY_LIST);
    }

    public void removeFollower(Integer u1, Integer u2){
        List<Integer> followers = followMap.getOrDefault(u1, new ArrayList<>());
        followers.remove(Integer.valueOf(u2));
    }

}
