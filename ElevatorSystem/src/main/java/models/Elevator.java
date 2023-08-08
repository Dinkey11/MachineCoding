package models;

import java.util.PriorityQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class Elevator {

    private static AtomicInteger ID_GENERATOR = new AtomicInteger(1);

    private Integer id;
    private Integer noOfFloors;
    private Integer currentFloor;
    private Direction direction;
    private PriorityQueue<Integer> activeUpRq;
    private PriorityQueue<Integer> activeDownRq;

    public Elevator(Integer noOfFloors) {
        this.id = ID_GENERATOR.getAndIncrement();
        this.noOfFloors = noOfFloors;
        this.direction = Direction.REST;
        this.activeDownRq = new PriorityQueue<>((a, b) -> (b-a));
        this.activeUpRq = new PriorityQueue<>();
        this.currentFloor = 0;
    }

    public Integer getId() {
        return id;
    }

    public Integer getNoOfFloors() {
        return noOfFloors;
    }

    public void setNoOfFloors(Integer noOfFloors) {
        this.noOfFloors = noOfFloors;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public PriorityQueue<Integer> getActiveUpRq() {
        return activeUpRq;
    }

    public void setActiveUpRq(PriorityQueue<Integer> activeUpRq) {
        this.activeUpRq = activeUpRq;
    }

    public PriorityQueue<Integer> getActiveDownRq() {
        return activeDownRq;
    }

    public void setActiveDownRq(PriorityQueue<Integer> activeDownRq) {
        this.activeDownRq = activeDownRq;
    }

    public Integer getCurrentFloor() {
        return currentFloor;
    }

    public void setCurrentFloor(Integer currentFloor) {
        this.currentFloor = currentFloor;
    }
}
