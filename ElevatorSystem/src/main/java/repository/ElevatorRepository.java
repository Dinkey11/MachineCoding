package repository;

import models.Elevator;

import java.util.HashMap;

public class ElevatorRepository {

    public static ElevatorRepository elevatorRepository = null;

    private HashMap<Integer, Elevator> elevatorMap;

    public static ElevatorRepository getInstance(){
        if(elevatorRepository == null){
            elevatorRepository = new ElevatorRepository();
            return elevatorRepository;
        }
        return elevatorRepository;
    }

    public ElevatorRepository() {
        this.elevatorMap = new HashMap<>();
    }

    //Add new Elevator
    public Elevator addElevator(Integer noOfFloors){
        Elevator elevator = new Elevator(noOfFloors);
        elevatorMap.put(elevator.getId(), elevator);
        return elevator;
    }

    //RemoveElevator
    public void removeElevator(Integer elevatorId){
        elevatorMap.remove(elevatorId);
        return;
    }

    //getElevator
    public Elevator getElevator(Integer elevatorId){
        return elevatorMap.getOrDefault(elevatorId, null);
    }
}
