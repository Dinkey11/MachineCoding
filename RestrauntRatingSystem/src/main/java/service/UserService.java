package service;

import models.Restraunt;
import models.User;
import repository.UserRepository;

import java.util.HashMap;
import java.util.Set;

public class UserService {

    private static UserService userService = null;

    public static UserService getInstance(){
        if(userService == null)
            userService = new UserService();

        return userService;
    }

    private UserRepository userRepository;
    private RestrauntService restrauntService;

    public UserService() {
        this.userRepository = UserRepository.getInstance();
        this.restrauntService = RestrauntService.getInstance();
    }

    public User addUser(String name, Integer pincode){
        User user = new User(name, pincode);
        userRepository.addUser(user);
        return user;
    }

    public User getUser(Integer id){
        return userRepository.getUser(id);
    }

    public User addRestruant(Integer userId, Integer branchId){
        User user = getUser(userId);
        if(user == null ) return null;

        Restraunt restraunt = restrauntService.getRetraunat(branchId);
        if(restraunt == null) return user;

        Set<Restraunt> restraunts = user.getLikedRestraunts();
        restraunts.add(restraunt);
        user.setLikedRestraunts(restraunts);

        return user;
    }



}
