package models;

import java.util.concurrent.atomic.AtomicInteger;

public class Admin {

    private static AtomicInteger ADMIN_ID_GENERATOR = new AtomicInteger(1);

    private Integer id;
    private String name;
    private Integer gymId;

    public Admin(String name, Integer gymId) {
        this.id = ADMIN_ID_GENERATOR.getAndIncrement();
        this.name = name;
        this.gymId = gymId;
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

    public Integer getGymId() {
        return gymId;
    }

    public void setGymId(Integer gymId) {
        this.gymId = gymId;
    }
}
