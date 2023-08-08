package service;

import models.FoodItem;
import repository.FoodItemRepository;

public class FoodService {

    private static FoodService foodService = null;

    public static FoodService getInstance(){
        if(foodService == null){
            foodService = new FoodService();
        }
        return foodService;
    }

    private FoodItemRepository repository;

    public FoodService() {
        this.repository = FoodItemRepository.getInstance();
    }

    public FoodItem addFoodItem(String name){
        FoodItem foodItem = new FoodItem(name);
        repository.addFoodItem(foodItem);
        return foodItem;
    }

    public FoodItem getFoodItem(Integer id){
        return repository.getFoodIetm(id);
    }
}
