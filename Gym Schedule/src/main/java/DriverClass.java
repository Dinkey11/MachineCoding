import models.*;
import service.GymClassService;
import service.GymService;
import service.UserService;

import java.util.List;

public class DriverClass {

    public static void main(String[] args){

        UserService userService = UserService.getInstance();
        GymClassService gymClassService = GymClassService.getInstance();
        GymService gymService = GymService.getInstance();

        Customer c1 = userService.addCustomer("Dinkey", UserType.CUSTOMER);
        Customer c2 = userService.addCustomer("Rashi", UserType.CUSTOMER);
        Customer c3 = userService.addCustomer("Shruti", UserType.CUSTOMER);
        Customer c4 = userService.addCustomer("Shubh", UserType.CUSTOMER);
        Customer c5 = userService.addCustomer("KaNHA", UserType.CUSTOMER);
        Customer c6 = userService.addCustomer("SOHAN", UserType.ADMIN);

        Gym G1 = gymService.addGym("Gym1", 12345, 100);
        Gym G2 = gymService.addGym("Gym2", 44634, 150);
        Gym G3 = gymService.addGym("Gym3", 12345, 50);

        GymClass class1 = gymClassService.addNewClass(G1.getId(), 2, 7, 8, ClassType.CARDIO);
        GymClass class2 = gymClassService.addNewClass(G1.getId(), 40, 7, 8, ClassType.YOGA);
        GymClass class3 = gymClassService.addNewClass(G1.getId(), 60, 9, 10, ClassType.CARDIO);
        GymClass class9 = gymClassService.addNewClass(G1.getId(), 50, 9, 10, ClassType.WEIGHTS);
        GymClass class4 = gymClassService.addNewClass(G2.getId(), 40, 7, 8, ClassType.CARDIO);
        GymClass class5 = gymClassService.addNewClass(G2.getId(), 40, 7, 8, ClassType.CARDIO);
        GymClass class6 = gymClassService.addNewClass(G3.getId(), 40, 7, 8, ClassType.CARDIO);
        GymClass class7 = gymClassService.addNewClass(G3.getId(), 60, 16, 18, ClassType.YOGA);
        GymClass class8 = gymClassService.addNewClass(G3.getId(), 40, 14, 15, ClassType.CARDIO);


        Boolean flag = userService.bookClass(c1.getId(), G1.getId(), class1.getId());
        Boolean flag345 = userService.bookClass(c1.getId(), G1.getId(), class2.getId());
        Boolean flagwer = userService.bookClass(c1.getId(), G1.getId(), class3.getId());
        Boolean flag2 = userService.bookClass(c2.getId(), G1.getId(), class1.getId());
        Boolean flag3 = userService.bookClass(c3.getId(), G1.getId(), class1.getId());
        Boolean flag4 = userService.bookClass(c2.getId(), G1.getId(), class1.getId());
        Boolean flag5 = userService.bookClass(c2.getId(), G1.getId(), class1.getId());

        List<GymClass> allBookings = userService.getAllBookings(c1.getId());

        userService.removeClass(c1.getId(), class1.getId());

        Boolean flagnow = gymService.removeClass(G1.getId(), class1.getId());

    }
}
