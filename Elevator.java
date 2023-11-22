import java.util.ArrayList;
import java.util.List;

public class Elevator {
    // Fields
    private int currentFloor; // Tracks the current floor of the elevator.
    private boolean movingUp; // Indicates if the elevator is moving up.
    private final List<Passenger> passengers; // Stores the passengers currently in the elevator.
    private final int capacity; // Maximum number of passengers the elevator can hold.
    private final List<Integer> destinationFloors; // Stores the floors that the elevator needs to stop at.

    // Constructor
    public Elevator(int capacity) {
        this.currentFloor = 1; // Elevator starts on the first floor.
        this.movingUp = true; // Initially set to move up.
        this.passengers = new ArrayList<>(); // Initialize the passengers list.
        this.capacity = capacity; // Set the elevator's capacity.
        this.destinationFloors = new ArrayList<>(); // Initialize the list of destination floors.
    }

    // Getters and Setters
    public int getCurrentFloor() {
        return currentFloor;
    }

    public void move() {
        // If there are destination floors, move the elevator up or down.
        if (!destinationFloors.isEmpty()) {
            if (movingUp) {
                currentFloor++; // Move up a floor.
            } else {
                currentFloor--; // Move down a floor.
            }
            checkDestinationFloor(); // Check if the elevator has reached a destination floor.
        }
    }

    public void boardPassenger(Passenger p) {
        // Adds a passenger to the elevator if there is space.
        if (this.passengers.size() >= capacity) {
            throw new IllegalStateException("Elevator is full"); // Throw exception if elevator is full.
        }
        this.passengers.add(p); // Add passenger to the list.
        addDestinationFloor(p.getDestinationFloor()); // Add passenger's destination to the list of floors.
    }

    private void addDestinationFloor(int destinationFloor) {
        // Add a floor to the destination list if it's not already there.
        if (!destinationFloors.contains(destinationFloor)) {
            destinationFloors.add(destinationFloor);
        }
    }

    public void releasePassenger(Passenger p) {
        // Remove a passenger from the elevator.
        this.passengers.remove(p);
    }

    private void checkDestinationFloor() {
        // Check if the current floor is a destination floor.
        if (destinationFloors.contains(currentFloor)) {
            unloadPassenger(); // Unload passengers for this floor.
            destinationFloors.remove(Integer.valueOf(currentFloor)); // Remove this floor from the destination list.
        }
    }

    private void unloadPassenger() {
        // Unload passengers who have reached their destination.
        List<Passenger> passengersToUnload = new ArrayList<>(this.passengers);
        for (Passenger passenger : passengersToUnload) {
            if (passenger.getDestinationFloor() == currentFloor) {
                passenger.exitElevator();
                releasePassenger(passenger);
            }
        }
    }

    public boolean isMovingUp() {
        return movingUp;
    }

    public void setMovingUp(boolean movingUp) {
        this.movingUp = movingUp; // Set the elevator's direction.
    }

    public int getCapacity() {
        return capacity;
    }

    public List<Passenger> getPassengers() {
        return passengers;
    }

    public List<Integer> getDestinationFloors() {
        return destinationFloors;
    }

    public void update() {
        // Update the state of the elevator.
        move(); // Move the elevator.
        checkDestinationFloor(); // Check if the elevator has reached a destination floor.
    }
}
