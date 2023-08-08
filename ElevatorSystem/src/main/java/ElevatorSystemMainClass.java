import models.Direction;
import models.Elevator;
import models.Source;
import service.ElevatorService;

public class ElevatorSystemMainClass {

    public static void main(String[] args){
        ElevatorService elevatorService = ElevatorService.getInstance();
        Elevator elevator = elevatorService.addElevator(15);

        elevatorService.addFloorRequest(3, Direction.UP, elevator.getId(), Source.INSIDE, null);
        elevatorService.addFloorRequest(5, Direction.UP, elevator.getId(), Source.INSIDE, null);
        elevatorService.addFloorRequest(15, Direction.UP, elevator.getId(), Source.OUTSIDE, 9);
        elevatorService.addFloorRequest(1, Direction.DOWN, elevator.getId(), Source.INSIDE, null);
        elevatorService.addFloorRequest(2, Direction.DOWN, elevator.getId(), Source.INSIDE, null);
        elevatorService.addFloorRequest(0, Direction.DOWN, elevator.getId(), Source.OUTSIDE, 4);

        elevatorService.runElevator(elevator.getId());

        System.out.println(elevator.getCurrentFloor());

    }
}
