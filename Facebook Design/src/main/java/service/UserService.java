package service;

import mapper.UserRqToUserMapper;
import models.request.AddUserRq;
import models.user.Address;
import models.user.Gender;
import models.user.User;
import repository.UserRepository;

import java.time.LocalDate;

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

    public void addUser(AddUserRq userRq){
        User user = UserRqToUserMapper.getUserFromUserRq(userRq);
        try{
            userRepository.addUser(user);
        }catch (Exception ex){
            System.out.println("Could not add user with error =" + ex.getMessage());
        }
    }

    public User getUser(Integer id){
        try{
            return userRepository.getUser(id);
        }catch (Exception e) {
            System.out.println("Could not get user with error =" +e.getMessage());
        }
        return null;
    }



}
