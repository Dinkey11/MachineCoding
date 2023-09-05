package models;

import java.util.HashMap;
import java.util.HashSet;
import java.util.concurrent.atomic.AtomicInteger;

public class Gym {

    private static AtomicInteger ID_GENERATOR = new AtomicInteger(1);

    private Integer id;
    private String name;
    private Integer location;//PINCODE
    private Integer maxCapacity;
    private Integer remainingCapacity;
    private HashMap<Integer, GymClass> classes;
    private Integer adminId;

    public Gym(String name, Integer location, Integer maxCapacity) {
        this.id = ID_GENERATOR.getAndIncrement();
        this.name = name;
        this.location = location;
        this.maxCapacity = maxCapacity;
        this.remainingCapacity = maxCapacity;
        this.classes = new HashMap<>();
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

    public Integer getLocation() {
        return location;
    }

    public void setLocation(Integer location) {
        this.location = location;
    }

    public Integer getMaxCapacity() {
        return maxCapacity;
    }

    public void setMaxCapacity(Integer maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    public Integer getRemainingCapacity() {
        return remainingCapacity;
    }

    public void setRemainingCapacity(Integer remainingCapacity) {
        this.remainingCapacity = remainingCapacity;
    }

    public HashMap<Integer, GymClass> getClasses() {
        return classes;
    }

    public void setClasses(HashMap<Integer, GymClass> classes) {
        this.classes = classes;
    }

    public Integer getAdminId() {
        return adminId;
    }

    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }
}
