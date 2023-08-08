import models.FoodItem;
import models.User;
import service.FoodService;
import service.RatingService;
import service.RestrauntService;
import service.UserService;

public class RestrauntProblemMainClass {

    public static void main(String[] args){
        UserService userService = UserService.getInstance();
        FoodService foodService = FoodService.getInstance();
        RestrauntService restrauntService = RestrauntService.getInstance();
        RatingService ratingService = RatingService.getInstance();

        FoodItem f1 = foodService.addFoodItem("Pasta");
        FoodItem f2 = foodService.addFoodItem("Maggie");
        FoodItem f3 = foodService.addFoodItem("Salad");
        FoodItem f4 = foodService.addFoodItem("Burger");

        User u1 = userService.addUser("Dinkey", 12345);
        User u2 = userService.addUser("Rashi", 12245);
        User u3 = userService.addUser("Shruti", 16345);
        User u4 = userService.addUser("Shubh", 12344);
        User u5 = userService.addUser("kanha", 19845);


    }
}
