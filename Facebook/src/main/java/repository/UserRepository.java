package repository;

import models.User;

import java.util.HashMap;

public class UserRepository {

    public static UserRepository userRepository = null;

    private HashMap<Integer, User> userMap;

    public static UserRepository getInstance(){
        if(userRepository == null){
            return userRepository = new UserRepository();
        }
        return userRepository;
    }

    public UserRepository() {
        userMap = new HashMap<>();
    }

    public void addUser(User user) throws Exception {
        if(user.getId() == null){
            throw new Exception("User Id cannot be null");
        }
        userMap.put(user.getId(), user);
        return;
    }

    public User getUser(Integer id) throws Exception {
        User user = userMap.getOrDefault(id, null);
        return user;
    }

}
