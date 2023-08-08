package models;

public class Request {

    private int currentFloor;
    private int requestedFloor;
    private Direction directionRequested;
    private Source source;


    public int getCurrentFloor() {
        return currentFloor;
    }

    public void setCurrentFloor(int currentFloor) {
        this.currentFloor = currentFloor;
    }

    public int getRequestedFloor() {
        return requestedFloor;
    }

    public void setRequestedFloor(int requestedFloor) {
        this.requestedFloor = requestedFloor;
    }

    public Direction getDirectionRequested() {
        return directionRequested;
    }

    public void setDirectionRequested(Direction directionRequested) {
        this.directionRequested = directionRequested;
    }

    public Source getSource() {
        return source;
    }

    public void setSource(Source source) {
        this.source = source;
    }
}
