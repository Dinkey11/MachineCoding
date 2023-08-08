package repository;

import models.User;

import java.util.HashMap;

public class UserRepository {

    private static UserRepository userRepository=null;

    public static UserRepository getInstance(){
        if(userRepository == null){
            userRepository = new UserRepository();
        }
        return userRepository;
    }

    private HashMap<Integer, User> userHashMap;

    public UserRepository() {
        this.userHashMap = new HashMap<>();
    }

    public void addUser(User user){
        userHashMap.put(user.getId(), user);
        return;
    }

    public User getUser(Integer id){
        return userHashMap.getOrDefault(id, null);
    }
}
