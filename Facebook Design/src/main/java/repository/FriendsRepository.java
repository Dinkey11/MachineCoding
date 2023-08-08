package repository;

import models.friends.FriendRequest;
import models.friends.Status;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class FriendsRepository {

    public static FriendsRepository friendsRepository = null;

    private HashMap<Integer, HashMap<Integer, Boolean>> friendsHashMap;
    private HashMap<Integer, HashMap<Integer, FriendRequest>> incomingFriendRequest;

    public static FriendsRepository getInstance(){
        if(friendsRepository == null){
            friendsRepository = new FriendsRepository();
            return friendsRepository;
        }
        return friendsRepository;
    }

    public FriendsRepository() {
        friendsHashMap = new HashMap<>();
        incomingFriendRequest = new HashMap<>();
    }

    public void addFriends(Integer user1, Integer user2 ){
        HashMap<Integer, Boolean> friendsofUser1 = friendsHashMap.getOrDefault(user1, new HashMap<>());
        HashMap<Integer, Boolean> friendsofUser2 = friendsHashMap.getOrDefault(user2, new HashMap<>());
        friendsofUser2.put(user1, true);
        friendsofUser1.put(user2, true);
        friendsHashMap.put(user1, friendsofUser1);
        friendsHashMap.put(user2, friendsofUser2);
        return;
    }

    public void addFriendRequest(Integer user1, Integer user2 ){
        FriendRequest friendRequest = new FriendRequest();
        friendRequest.setReceiver(user2);
        friendRequest.setSender(user1);
        friendRequest.setAcceptedDate(null);
        friendRequest.setSendDate(LocalDate.now());
        friendRequest.setStatus(Status.PENDING);

        HashMap<Integer, FriendRequest> requests = incomingFriendRequest.getOrDefault(user1, new HashMap<>());
        requests.put(user1, friendRequest);
        incomingFriendRequest.put(user2, requests);
    }

    public void updateFriendRequest(Integer sender, Integer reciever,  Status status){
        HashMap<Integer, FriendRequest> requestHashMap = incomingFriendRequest.getOrDefault(reciever, null);
        if(requestHashMap == null) return;
        FriendRequest request = requestHashMap.getOrDefault(sender, null);
        if(request == null) return;
        request.setStatus(status);
        request.setAcceptedDate(LocalDate.now());
        requestHashMap.put(sender, request);
        incomingFriendRequest.put(reciever, requestHashMap);
        if(status.equals(Status.ACCEPTED)) addFriends(sender, reciever);
    }

    public List<Integer> getFriends(Integer userId){
        HashMap<Integer, Boolean> friends = friendsHashMap.getOrDefault(userId, null);
        if(friends == null) return Collections.EMPTY_LIST;
        List<Integer> users = new ArrayList<>();
        friends.forEach((k, v)-> users.add(k));
        return users;
    }

    public Status getReqStatus(Integer u1, Integer u2){
        HashMap<Integer, FriendRequest> requestHashMap = incomingFriendRequest.getOrDefault(u1, null);
        if(requestHashMap == null) return Status.UNKNOWN;
        FriendRequest request = requestHashMap.getOrDefault(u2, null);
        if(request == null) return Status.UNKNOWN;
        return request.getStatus();
    }

    public void removeFriend(Integer u1, Integer u2){
        HashMap<Integer, Boolean> friendsofUser1 = friendsHashMap.getOrDefault(u1, new HashMap<>());
        HashMap<Integer, Boolean> friendsofUser2 = friendsHashMap.getOrDefault(u2, new HashMap<>());
        friendsofUser1.remove(u2);
        friendsofUser2.remove(u1);
        return;
    }
}
