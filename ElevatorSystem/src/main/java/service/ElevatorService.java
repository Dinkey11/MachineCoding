package service;

import models.Direction;
import models.Elevator;
import models.Source;
import repository.ElevatorRepository;

import java.util.PriorityQueue;

public class ElevatorService {

    public static ElevatorService elevatorService = null;

    private ElevatorRepository elevatorRepository;

    public static ElevatorService getInstance(){
        if(elevatorService == null){
            elevatorService = new ElevatorService();
            return elevatorService;
        }
        return elevatorService;
    }

    public ElevatorService() {
        this.elevatorRepository = ElevatorRepository.getInstance();
    }

    //AddNewElevator
    public Elevator addElevator(Integer noOfFloors){
        Elevator elevator = elevatorRepository.addElevator(noOfFloors);
        return elevator;
    }

    //AddRequest
    public void addFloorRequest(Integer floor, Direction direction, Integer elevatorId, Source source, Integer requestedFrom){
        Elevator elevator = elevatorRepository.getElevator(elevatorId);
        if(elevator == null) return;

        PriorityQueue<Integer> upRq = elevator.getActiveUpRq();
        PriorityQueue<Integer> downRq = elevator.getActiveDownRq();

        if (direction.equals(Direction.UP)) {
            upRq.add(floor);
        }else if(direction.equals(Direction.DOWN)){
            downRq.add(floor);
        }

        if(source.equals(Source.OUTSIDE)){
            if (direction.equals(Direction.UP)) {
                upRq.add(requestedFrom);
            }else if(direction.equals(Direction.DOWN)){
                downRq.add(requestedFrom);
            }
        }
        elevator.setActiveDownRq(downRq);
        elevator.setActiveUpRq(upRq);
        return;
    }


    //processRequests
    public void runElevator(Integer elevatorId){

        Elevator elevator = elevatorRepository.getElevator(elevatorId);
        if(elevator == null) return;

        while(!elevator.getActiveUpRq().isEmpty() || !elevator.getActiveDownRq().isEmpty()){
            processRequests(elevator);
        }

        if(elevator.getActiveUpRq().isEmpty() || elevator.getActiveDownRq().isEmpty()) elevator.setDirection(Direction.REST);
    }

    private void processRequests(Elevator elevator){
        Direction direction = elevator.getDirection();
        if(direction.equals(Direction.UP) || direction.equals(Direction.REST))
            processUpRequests(elevator, elevator.getCurrentFloor());
        else
            processDownRequests(elevator, elevator.getCurrentFloor());
    }

    private void processUpRequests(Elevator elevator, Integer currentFloor) {
        PriorityQueue<Integer> upRq = elevator.getActiveUpRq();
        if(upRq.isEmpty() && !elevator.getActiveDownRq().isEmpty()){
            System.out.println("Elevator changed direction at -> " + currentFloor + " now moving down");
            elevator.setDirection(Direction.DOWN);
            return;
        }
        else if(!upRq.isEmpty() && upRq.peek() >= currentFloor ) {
            int nextFloor = upRq.poll();
            while(!upRq.isEmpty() && upRq.peek() == nextFloor) upRq.poll();
            System.out.println("Elevator stopped at -> " + nextFloor + " while going up");
            elevator.setCurrentFloor(nextFloor);
            processUpRequests(elevator, nextFloor);
        }

        return;
    }

    private void processDownRequests(Elevator elevator, Integer currentFloor) {
        PriorityQueue<Integer> downRq = elevator.getActiveDownRq();
        if(downRq.isEmpty() && !elevator.getActiveUpRq().isEmpty()){
            System.out.println("Elevator changed direction at -> " + currentFloor + " now moving up");
            elevator.setDirection(Direction.UP);
            return;
        }
        else if(!downRq.isEmpty() && downRq.peek() <= currentFloor) {
            int nextFloor = downRq.poll();
            while(!downRq.isEmpty() && downRq.peek() == nextFloor) downRq.poll();
            System.out.println("Elevator stopped at -> " + nextFloor + " while going down");
            elevator.setCurrentFloor(nextFloor);
            processDownRequests(elevator, nextFloor);
        }
        return;
    }


}
