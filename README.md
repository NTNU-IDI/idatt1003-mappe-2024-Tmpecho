[![Review Assignment Due Date]([x]https://classroom.github.com/assets/deadline-readme-button-22041afd0340ce965d47ae6ef1cefeee28c7c493a6346c4f15d667ab976d596c.svg)](https://classroom.github.com/a/INcAwgxk)

# Portfolio project IDATT1003

STUDENT NAME = "Johannes Aamot-Skeidsvoll"  
STUDENT ID = "137410"

## Project description

This project is a text-based user interface (TUI) application for managing groceries and recipes, focusing on reducing
food waste by providing tools to track ingredients and suggest recipes based on available groceries. Users can add,
remove, and list groceries, as well as manage recipes in a cookbook. The program is designed to be modular and easily
extensible for future enhancements, such as future database integration and graphical user interfaces.

The program comes with a set of predefined groceries and recipes to demonstrate the functionality.

## Project structure

- **`src/main/java/edu/ntnu/idi/idatt`**: Contains all the source code for the application.
    - **`model`**: Data classes representing core entities like `Grocery`, `Recipe`, and `Storage`.
    - **`repository`**: In-memory storage classes for groceries and recipes, implementing repository interfaces.
    - **`controller`**: Controllers to manage interactions between the data models and user interface.
    - **`service`**: Business logic like ingredient validation and recipe suggestions.
    - **`view`**: Display classes responsible for formatting outputs in the text-based interface.
    - **`ui`**: Main user interface classes, including `TextMenuInterface` for the TUI implementation.
    - **`util`**: Utility classes for input validation and data population.

- **`src/test/java/edu/ntnu/idi/idatt`**: Contains JUnit test classes for all critical components.
    - Tests are organized by package and validate functionality for models, controllers, repositories, and services.

## Link to repository

[GitHub Repository for IDATT1003 Portfolio Project](https://github.com/NTNU-IDI/idatt1003-mappe-2024-Tmpecho)

## How to run the project

1. Clone the repository to your local machine.
2. Ensure that your Java environment is set up.
3. Run the `Main.java` file located in the `src/main/java/edu/ntnu/idi/idatt` package where the main method is located.

```shell
java src/main/java/edu/ntnu/idi/idatt/Main.java
```

Running the main class will start the text-based user interface (TUI) for the application. Follow the on-screen
instructions to interact with the program.

## How to run the tests

To run the tests, run

```shell
mvn test
``` 

## References

[//]: # (TODO: Include references here, if any. For example, if you have used code from the course book, include a reference to the chapter.
Or if you have used code from a website or other source, include a link to the source.)