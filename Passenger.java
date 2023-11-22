public class Passenger {
    private int currentFloor; // The floor where the passenger currently is.
    private final int destinationFloor; // The floor the passenger wants to go to.
    private boolean inElevator; // Flag to indicate if the passenger is currently in an elevator.
    private int waitTime; // Tracks how long the passenger has been waiting for an elevator.
    private Elevator requestedElevator; // The elevator that the passenger has requested.

    /**
     * Constructor for the Passenger class.
     *
     * @param currentFloor The floor where the passenger starts.
     * @param destinationFloor The floor where the passenger wants to go.
     */
    public Passenger(int currentFloor, int destinationFloor) {
        // Validate floor numbers. They must be positive and different from each other.
        if (currentFloor < 1 || destinationFloor < 1 || currentFloor == destinationFloor) {
            throw new IllegalArgumentException("Invalid floor numbers");
        }
        this.currentFloor = currentFloor;
        this.destinationFloor = destinationFloor;
        this.inElevator = false; // Initially, the passenger is not in an elevator.
        this.waitTime = 0; // Initialize waiting time to 0.
    }

    /**
     * Calls an elevator for the passenger.
     *
     * @param controlSystem The elevator control system to request an elevator from.
     */
    public void callElevator(ElevatorControlSystem controlSystem) {
        // Request an elevator from the control system.
        // TODO: Add error handling if no elevator is available.
        requestedElevator = controlSystem.requestElevator(this);
    }

    /**
     * Passenger exits the elevator.
     * This method is called when the passenger reaches their destination floor.
     */
    // TODO: Review for efficiency improvements.
    public void exitElevator() {
        if (inElevator && requestedElevator.getCurrentFloor() == destinationFloor) {
            inElevator = false; // Mark the passenger as not in the elevator.
            requestedElevator.releasePassenger(this); // Release the passenger from the elevator.
            requestedElevator = null; // Clear the reference to the elevator.
        }
    }

    // Getters for the class fields.
    public int getCurrentFloor() {
        return currentFloor;
    }

    public int getDestinationFloor() {
        return destinationFloor;
    }

    public boolean isInElevator() {
        return inElevator;
    }

    public int getWaitTime() {
        return waitTime;
    }

    /**
     * Increments the waiting time of the passenger.
     * This could be called each simulation tick to simulate waiting.
     */
    public void incrementWaitTime() {
        this.waitTime++;
    }
}
