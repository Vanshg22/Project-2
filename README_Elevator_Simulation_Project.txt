Vansh Gupta 
20683066
Cs245 
Project 2

# Elevator Simulation Project

## Overview
This Java-based project simulates an elevator system, modeling the operations and efficiency of a typical elevator system in a building by managing multiple elevators, floors, and dynamically generated passengers.

## Features
- Configurable number of elevators, floors, and passenger generation probabilities.
- Dynamic passenger generation based on set probabilities.
- Simulation of passenger wait times and elevator movements.
- Reporting simulation results including average, shortest, and longest wait times.

## Acknowledgements
This project was developed with the assistance of various online resources and the invaluable help of friends who provided insights and guidance throughout the development process. Their contributions have been instrumental in bringing this project to fruition.

## Getting Started

### Prerequisites
- Java Development Kit (JDK) 1.8 or higher.

### Installation
1. Clone the repository:
   ```
   git clone [repository URL]
   ```
2. Navigate to the project directory:
   ```
   cd elevator-simulation
   ```

### Configuration
Configure the simulation parameters in the `default-config.properties` file. Available settings:

- `structures`: Type of data structure used (e.g., 'linked', 'array'). Default: 'linked'.
- `floors`: Total number of floors in the building. Default: 32.
- `passengers`: Probability of a passenger appearing at each tick (0 to 1). Default: 0.03.
- `elevators`: Number of elevators in the simulation. Default: 1.
- `elevatorCapacity`: Maximum capacity of each elevator. Default: 10.
- `duration`: Duration of the simulation in ticks. Default: 500.

### Running the Simulation
1. Compile the Java files:
   ```
   javac *.java
   ```
2. Run the `Main` class. Use an optional argument for a custom configuration file:
   ```
   java Main [optional: path to custom config file]
   ```
   Without an argument, the program defaults to `default-config.properties`.

## Usage Example
- Run with default configuration:
  ```
  java Main
  ```
- Run with a custom configuration file:
  ```
  java Main path/to/custom-config.properties
  ```

## Output
The simulation will output results, including average, longest, and shortest wait times for passengers.
