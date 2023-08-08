package models;

import java.util.concurrent.atomic.AtomicInteger;

public class ParentRestraunt {

    private static AtomicInteger ID_GENERATOR = new AtomicInteger(1);

    private Integer id;
    private String name;
    private Integer foodId;

    public ParentRestraunt(String name, Integer foodId) {
        this.id = ID_GENERATOR.getAndIncrement();
        this.name = name;
        this.foodId = foodId;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getFoodId() {
        return foodId;
    }

    public void setFoodId(Integer foodId) {
        this.foodId = foodId;
    }
}
