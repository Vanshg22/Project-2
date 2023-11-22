public class Main {
    public static void main(String[] args) {
        // Check if an argument is provided in the command line. If so, use it as the configuration file.
        // Otherwise, default to 'default-config.properties'.
        // This allows for flexibility in specifying different configuration files at runtime.
        String configFile = args.length > 0 ? args[0] : "default-config.properties";

        // Create a new instance of the Simulation class, passing the configFile as an argument.
        // The Simulation class presumably uses this config file to set up its parameters.
        Simulation simulation = new Simulation(configFile);

        // Run the simulation. This method starts the actual simulation process as defined in the Simulation class.
        // The details of the simulation process depend on the implementation of the Simulation class.
        simulation.runSimulation();
    }
}
