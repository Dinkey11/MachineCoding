package models;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

public class User {

    private static AtomicInteger ID_GENERATOR = new AtomicInteger(1);

    private Integer id;
    private String name;
    private Integer pincode;
    private Set<Restraunt> likedRestraunts;
    private HashMap<Integer, Integer> givenRatingsMap;

    public User(String name, Integer pincode) {
        this.id = ID_GENERATOR.getAndIncrement();
        this.name = name;
        this.pincode = pincode;
        this.likedRestraunts = new HashSet<>();
        this.givenRatingsMap = new HashMap<>();
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

    public Integer getPincode() {
        return pincode;
    }

    public void setPincode(Integer pincode) {
        this.pincode = pincode;
    }

    public Set<Restraunt> getLikedRestraunts() {
        return likedRestraunts;
    }

    public void setLikedRestraunts(Set<Restraunt> likedRestraunts) {
        this.likedRestraunts = likedRestraunts;
    }

    public HashMap<Integer, Integer> getGivenRatingsMap() {
        return givenRatingsMap;
    }

    public void setGivenRatingsMap(HashMap<Integer, Integer> givenRatingsMap) {
        this.givenRatingsMap = givenRatingsMap;
    }
}
