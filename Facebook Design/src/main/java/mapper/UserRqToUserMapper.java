package mapper;

import models.request.AddUserRq;
import models.user.User;

public class UserRqToUserMapper {

    public static User getUserFromUserRq(AddUserRq userRq){
        User user = new User();
        user.setFirstName(userRq.getFirstName());
        user.setLastName(userRq.getLastName());
        user.setPhoneNumber(userRq.getPhoneNumber());
        user.setAddress(userRq.getAddress());
        user.setEmailId(userRq.getEmailId());
        user.setDateOfBirth(userRq.getDateOfBirth());
        user.setGender(userRq.getGender());
        return user;
    }
}
