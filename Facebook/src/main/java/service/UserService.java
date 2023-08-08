package service;

import models.User;
import repository.UserRepository;

import java.time.LocalDate;
import java.util.Set;

public class UserService {

    public static UserService userService = null;
    private UserRepository userRepository;

    public static UserService getInstance(){
        if(userService == null){
            userService = new UserService();
        }
        return userService;
    }

    public UserService(){
        userRepository = UserRepository.getInstance();
    }

    //Create User
    public Integer createUser(String firstName, String lastName, LocalDate dob){
        //Can add validation to check for existing user
        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setDateOfBirth(dob);

        try{
            userRepository.addUser(user);
        }catch (Exception e){
            System.out.println("Failed to save the user with Exception =" + e.getMessage());
        }

        return user.getId();
    }

    //getUser
    public User getUser(Integer userId){
        User user = null;
        try{
            user = userRepository.getUser(userId);
        }catch (Exception e){
            System.out.println("Failed to get user with exception="+e.getMessage());
            return null;
        }
        return user;
    }

    //Add Follower
    public void addFollower(Integer followedBy, Integer follows){
        System.out.println("Adding follower ="+ follows + " for user - " + followedBy );
        User user = getUser(followedBy);
        if(user == null) return;
        user.getFollowers().add(follows);
        return;
    }

    //Remove Follower
    public void removeFollower(Integer userId, Integer followerId){
        System.out.println("Removing follower ="+ followerId + " from user - " + userId );
        User user = getUser(userId);
        if(user == null) return;
        Set<Integer> followers = user.getFollowers();
        followers.remove(followerId);
        return;
    }

}
