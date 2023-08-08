package models;

import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class FoodItem {

    private static AtomicInteger ID_GENERATOR = new AtomicInteger(1);

    private Integer id;
    private String name;

    public FoodItem(String name) {
        this.id = ID_GENERATOR.getAndIncrement();
        this.name = name;
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
}
