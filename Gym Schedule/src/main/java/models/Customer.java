package models;

import service.UserService;

import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class Customer{

    private static AtomicInteger CUSTOMER_ID_GENERATOR = new AtomicInteger(1);

    private Integer id;
    private String name;
    private HashMap<Integer, GymClass> bookedClasses;
    private UserType userType;

    public Customer(String name, UserType userType) {
        this.id = CUSTOMER_ID_GENERATOR.getAndIncrement();
        this.bookedClasses = new HashMap<>();
        this.name = name;
        this.userType = userType;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HashMap<Integer, GymClass> getBookedClasses() {
        return bookedClasses;
    }

    public void setBookedClasses(HashMap<Integer, GymClass> bookedClasses) {
        this.bookedClasses = bookedClasses;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }
}
