package repository;

import exceptions.UserUpdateFailed;
import models.request.UpdateUserRq;
import models.user.User;

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

    public void addUser(User user) throws UserUpdateFailed {
        if(user.getId() == null){
            throw new UserUpdateFailed("User Id cannot be null");
        }
        userMap.put(user.getId(), user);
        return;
    }

    public User getUser(Integer id) throws UserUpdateFailed {
        User user = userMap.getOrDefault(id, null);
        return user;
    }

    public User updateUser(UpdateUserRq request) throws UserUpdateFailed {
        if(request == null || request.getUserId() == null){
            throw new UserUpdateFailed("Invalid User Update Request");
        }
        User user = userMap.getOrDefault(request.getUserId(), null);
        if(user == null) throw new IllegalArgumentException("No user found with id ={}" + request.getUserId());
        //Add code to update fields
        userMap.put(user.getId(), user);
        return user;
    }
}
