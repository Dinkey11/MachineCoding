package models;

import java.util.concurrent.atomic.AtomicInteger;

public class GymClass {

    private static AtomicInteger ID_GEN = new AtomicInteger(1);

    private Integer id;
    private Integer gymId;
    private Integer maxLimit;
    private Integer startHour;
    private Integer endHour;
    private ClassType classType;
    private Integer bookedCount;

    public GymClass(Integer gymId, Integer maxLimit, Integer startHour, Integer endHour, ClassType classType) {
        this.id = ID_GEN.getAndIncrement();
        this.gymId = gymId;
        this.maxLimit = maxLimit;
        this.startHour = startHour;
        this.endHour = endHour;
        this.classType = classType;
        this.bookedCount = 0;
    }

    public Integer getId() {
        return id;
    }

    public Integer getGymId() {
        return gymId;
    }

    public void setGymId(Integer gymId) {
        this.gymId = gymId;
    }

    public Integer getMaxLimit() {
        return maxLimit;
    }

    public void setMaxLimit(Integer maxLimit) {
        this.maxLimit = maxLimit;
    }

    public Integer getStartHour() {
        return startHour;
    }

    public void setStartHour(Integer startHour) {
        this.startHour = startHour;
    }

    public Integer getEndHour() {
        return endHour;
    }

    public void setEndHour(Integer endHour) {
        this.endHour = endHour;
    }

    public ClassType getClassType() {
        return classType;
    }

    public void setClassType(ClassType classType) {
        this.classType = classType;
    }

    public Integer getBookedCount() {
        return bookedCount;
    }

    public void setBookedCount(Integer bookedCount) {
        this.bookedCount = bookedCount;
    }
}
