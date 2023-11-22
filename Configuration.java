import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * The Configuration class is responsible for loading configuration settings from a properties file.
 * If the properties file cannot be found or read, default values are used.
 */
public class Configuration {
    // The properties object is used to store configuration settings.
    private final Properties properties = new Properties();

    // Default values for each configuration setting, used if the properties file is not found.
    private static final String DEFAULT_STRUCTURE = "linked";
    private static final int DEFAULT_FLOORS = 32;
    private static final double DEFAULT_PASSENGERS = 0.03;
    private static final int DEFAULT_ELEVATORS = 1;
    private static final int DEFAULT_ELEVATOR_CAPACITY = 10;
    private static final int DEFAULT_DURATION = 500;

    /**
     * Constructor for the Configuration class.
     *
     * @param filename The name (and path) of the properties file to load.
     */
    public Configuration(String filename) {
        try {
            // Attempt to load the properties file from the provided filename.
            properties.load(new FileInputStream(filename));
        } catch (IOException e) {
            // If an error occurs while reading the file, log an error message and use default values.
            System.err.println("Warning: Configuration file " + filename + " not found. Using default values.");
            setDefaultProperties();
        }
    }

    /**
     * Sets default values for all configuration settings.
     * This method is called if the properties file cannot be read or found.
     */
    private void setDefaultProperties() {
        properties.setProperty("structures", DEFAULT_STRUCTURE);
        properties.setProperty("floors", Integer.toString(DEFAULT_FLOORS));
        properties.setProperty("passengers", Double.toString(DEFAULT_PASSENGERS));
        properties.setProperty("elevators", Integer.toString(DEFAULT_ELEVATORS));
        properties.setProperty("elevatorCapacity", Integer.toString(DEFAULT_ELEVATOR_CAPACITY));
        properties.setProperty("duration", Integer.toString(DEFAULT_DURATION));
    }

    // Below are the getter methods for each configuration value.
    // These methods retrieve the respective values from the properties object,
    // falling back to default values if necessary.

    // Retrieves the 'structures' configuration value.
    public String getStructure() {
        return properties.getProperty("structures", DEFAULT_STRUCTURE);
    }

    // Retrieves the number of floors from the configuration.
    public int getFloors() {
        return Integer.parseInt(properties.getProperty("floors", String.valueOf(DEFAULT_FLOORS)));
    }

    // Retrieves the probability of passenger appearance.
    public double getPassengerProbability() {
        return Double.parseDouble(properties.getProperty("passengers", String.valueOf(DEFAULT_PASSENGERS)));
    }

    // Retrieves the number of elevators specified in the configuration.
    public int getElevators() {
        return Integer.parseInt(properties.getProperty("elevators", String.valueOf(DEFAULT_ELEVATORS)));
    }

    // Retrieves the maximum capacity of each elevator from the configuration.
    public int getElevatorCapacity() {
        return Integer.parseInt(properties.getProperty("elevatorCapacity", String.valueOf(DEFAULT_ELEVATOR_CAPACITY)));
    }

    // Retrieves the duration of the simulation in ticks.
    public int getDuration() {
        return Integer.parseInt(properties.getProperty("duration", String.valueOf(DEFAULT_DURATION)));
    }

    // Additional getter methods can be added here for other configuration settings as needed.
}
