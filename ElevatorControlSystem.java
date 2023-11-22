import java.util.List;

public class ElevatorControlSystem {
    private final List<Elevator> elevators; // A list of Elevator objects managed by the system.

    /**
     * Constructor for ElevatorControlSystem.
     *
     * @param elevators A list of Elevator objects to be managed.
     */
    public ElevatorControlSystem(List<Elevator> elevators){
        this.elevators = elevators;
    }

    /**
     * Requests an elevator for a given passenger.
     * This method finds the closest available elevator to the passenger's current floor.
     *
     * @param passenger The passenger who is requesting the elevator.
     * @return The chosen Elevator object or null if no elevator is available.
     */
    public Elevator requestElevator(Passenger passenger) {
        Elevator bestElevator = null;
        int minDistance = Integer.MAX_VALUE;
        for (Elevator elevator : elevators) {
            int distance = Math.abs(elevator.getCurrentFloor() - passenger.getCurrentFloor());
            if (distance < minDistance) {
                minDistance = distance;
                bestElevator = elevator;
            }
        }
        return bestElevator;
    }

    /**
     * Assigns the best available elevator to a passenger.
     *
     * @param passenger The passenger to be added to an elevator.
     */
    public void addPassenger(Passenger passenger) {
        Elevator bestElevator = findBestElevatorForPassenger(passenger);
        if (bestElevator != null) {
            bestElevator.boardPassenger(passenger);
        }
    }

    /**
     * Advances the state of the simulation by one step.
     * This method should be called periodically to update the state of each elevator.
     */
    public void step() {
        for (Elevator elevator : elevators) {
            elevator.update(); // Assuming there's an update method in Elevator class.
        }
    }

    /**
     * Finds the best elevator for a given passenger.
     * The criteria for the best elevator include distance to the passenger and current elevator capacity.
     *
     * @param passenger The passenger for whom an elevator is being requested.
     * @return The chosen Elevator object or null if no elevator is available.
     */
    private Elevator findBestElevatorForPassenger(Passenger passenger) {
        Elevator bestElevator = null;
        int minDistance = Integer.MAX_VALUE;

        for (Elevator elevator : elevators) {
            if (elevator.getPassengers().size() < elevator.getCapacity()) {
                int distance = Math.abs(elevator.getCurrentFloor() - passenger.getCurrentFloor());

                boolean isElevatorMovingTowardsPassenger = (elevator.isMovingUp() && passenger.getCurrentFloor() > elevator.getCurrentFloor()) ||
                        (!elevator.isMovingUp() && passenger.getCurrentFloor() < elevator.getCurrentFloor());

                if (distance < minDistance && isElevatorMovingTowardsPassenger) {
                    minDistance = distance;
                    bestElevator = elevator;
                }
            }
        }

        // If no best elevator is found in the first pass, find the closest elevator regardless of direction.
        if (bestElevator == null) {
            for (Elevator elevator : elevators) {
                if (elevator.getPassengers().size() < elevator.getCapacity()) {
                    int distance = Math.abs(elevator.getCurrentFloor() - passenger.getCurrentFloor());
                    if (distance < minDistance) {
                        minDistance = distance;
                        bestElevator = elevator;
                    }
                }
            }
        }

        return bestElevator;
    }
}
