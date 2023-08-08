package models;

import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class Restraunt {
    private static AtomicInteger ID_GENERATOR = new AtomicInteger(1);

    private Integer id;
    private Integer parentId;
    private Integer pincode;
    private Integer foodId;
    private HashMap<Integer, Integer> userRatingMap;

    public Restraunt(Integer parentId, Integer pincode, Integer foodId) {
        this.id = ID_GENERATOR.getAndIncrement();
        this.parentId = parentId;
        this.pincode = pincode;
        this.foodId = foodId;
        this.userRatingMap = new HashMap<>();
    }

    public Integer getId() {
        return id;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Integer getPincode() {
        return pincode;
    }

    public void setPincode(Integer pincode) {
        this.pincode = pincode;
    }

    public Integer getFoodId() {
        return foodId;
    }

    public void setFoodId(Integer foodId) {
        this.foodId = foodId;
    }

    public HashMap<Integer, Integer> getUserRatingMap() {
        return userRatingMap;
    }

    public void setUserRatingMap(HashMap<Integer, Integer> userRatingMap) {
        this.userRatingMap = userRatingMap;
    }
}
