import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Simulation {
    private final Configuration config; // Configuration object to load settings.
    private ElevatorControlSystem controlSystem; // Manages the elevators.
    private int ticks; // Duration of the simulation in ticks.
    private Random random; // Random number generator for creating passengers.
    private final List<Passenger> allPassengers; // List to keep track of all passengers.

    /**
     * Constructor for the Simulation class.
     *
     * @param configFile The name of the configuration file.
     */
    public Simulation(String configFile) {
        config = new Configuration(configFile); // Load configuration settings.
        allPassengers = new ArrayList<>(); // Initialize the list of passengers.
        initializeSimulation(); // Initialize the simulation with provided settings.
    }

    /**
     * Initializes the simulation environment.
     * This includes creating elevators and setting up the control system.
     */
    private void initializeSimulation() {
        ArrayList<Elevator> elevators = new ArrayList<>();
        for (int i = 0; i < config.getElevators(); i++) {
            elevators.add(new Elevator(config.getElevatorCapacity())); // Create elevators based on configuration.
        }
        controlSystem = new ElevatorControlSystem(elevators); // Initialize the elevator control system.
        ticks = config.getDuration(); // Set the duration of the simulation.
        random = new Random(); // Initialize the random number generator.
    }

    /**
     * Runs the simulation for the configured number of ticks.
     */
    public void runSimulation() {
        for (int tick = 0; tick < ticks; tick++) {
            generatePassengers(); // Generate passengers for this tick.
            controlSystem.step(); // Update the state of the elevator system.
            updatePassengerWaitTimes(); // Update the wait times of all passengers.
        }
        reportResults(); // Report the results of the simulation.
    }

    /**
     * Generates passengers on each floor based on a probability.
     */
    private void generatePassengers() {
        int floors = config.getFloors();
        double probability = config.getPassengerProbability(); // Probability of passenger appearance.
        for (int floor = 1; floor <= floors; floor++) {
            if (random.nextDouble() < probability) { // Check if a passenger appears on this floor.
                int destinationFloor;
                do {
                    destinationFloor = random.nextInt(floors) + 1; // Randomly assign a destination floor.
                } while (destinationFloor == floor); // Ensure destination is different from current floor.
                Passenger passenger = new Passenger(floor, destinationFloor); // Create a new passenger.
                controlSystem.addPassenger(passenger); // Add the passenger to the elevator system.
                allPassengers.add(passenger); // Add passenger to the list for tracking.
            }
        }
    }

    /**
     * Updates the wait time of each passenger who is not yet in an elevator.
     */
    private void updatePassengerWaitTimes() {
        for (Passenger passenger : allPassengers) {
            if (!passenger.isInElevator()) {
                passenger.incrementWaitTime(); // Increment wait time for each waiting passenger.
            }
        }
    }

    /**
     * Reports the results of the simulation, including average, longest, and shortest wait times.
     */
    private void reportResults() {
        int totalWaitTime = 0;
        int longestWaitTime = 0;
        int shortestWaitTime = Integer.MAX_VALUE; // Start with the maximum possible value.
        for (Passenger passenger : allPassengers) {
            int waitTime = passenger.getWaitTime();
            totalWaitTime += waitTime;
            longestWaitTime = Math.max(longestWaitTime, waitTime); // Find the longest wait time.
            shortestWaitTime = Math.min(shortestWaitTime, waitTime); // Find the shortest wait time.
        }
        double averageWaitTime = allPassengers.isEmpty() ? 0 : (double) totalWaitTime / allPassengers.size();
        System.out.println("Simulation Results:");
        System.out.println("Average Wait Time: " + averageWaitTime);
        System.out.println("Longest Wait Time: " + longestWaitTime);
        System.out.println("Shortest Wait Time: " + (shortestWaitTime == Integer.MAX_VALUE ? 0 : shortestWaitTime));
    }
}
