package repository;

import models.FoodItem;
import models.ParentRestraunt;

import java.util.HashMap;

public class FoodItemRepository {

    private static FoodItemRepository foodItemRepository=null;

    public static FoodItemRepository getInstance(){
        if(foodItemRepository == null){
            foodItemRepository = new FoodItemRepository();
        }
        return foodItemRepository;
    }

    private HashMap<Integer, FoodItem> foodItemHashMap;

    public FoodItemRepository() {
        this.foodItemHashMap = new HashMap<>();
    }

    public void addFoodItem(FoodItem foodItem){
        foodItemHashMap.put(foodItem.getId(), foodItem);
        return;
    }

    public FoodItem getFoodIetm(Integer id){
        return foodItemHashMap.getOrDefault(id, null);
    }

}
