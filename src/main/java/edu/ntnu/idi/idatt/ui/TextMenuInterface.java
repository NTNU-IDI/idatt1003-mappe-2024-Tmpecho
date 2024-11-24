package edu.ntnu.idi.idatt.ui;

import edu.ntnu.idi.idatt.model.MeasurementUnit;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.Scanner;

/** Text-based menu interface for the user. */
public class TextMenuInterface extends UserInterface {
  Scanner scanner = new Scanner(System.in);

  /** Starts the text-based menu interface. */
  public void start() {
    int choice = -1;
    while (choice != 0) {
      printMenu();
      choice = readInt("Enter choice: ");
      switch (choice) {
        case 1 -> addGrocery();
        case 2 -> removeGrocery();
        case 3 -> listGroceries();
        case 4 -> listExpiredGroceries();
        case 5 -> calculateTotalValue();
        case 0 -> System.out.println("Exiting...");
        default -> System.out.println("Invalid choice");
      }
    }
  }

  /** Prints the menu of choices for the user to perform. */
  public void printMenu() {
    System.out.println("\nMenu:");
    System.out.println("1. Add grocery");
    System.out.println("2. Remove grocery");
    System.out.println("3. List groceries");
    System.out.println("4. List expired groceries");
    System.out.println("5. Calculate total value of groceries");
    System.out.println("0. Exit");
  }

  /**
   * Reads a name from the user with a prompt.
   *
   * @return the name entered by the user
   */
  private String readName() {
    while (true) {
      System.out.print("Enter name: ");
      String name = scanner.nextLine();
      if (name == null || name.isEmpty()) {
        System.out.println("Name cannot be empty. Please enter a name.");
        continue;
      }
      return name;
    }
  }

  /**
   * Reads an integer from the user with a prompt.
   *
   * @param prompt the prompt to display to the user
   * @return the integer entered by the user
   */
  private int readInt(String prompt) {
    while (true) {
      System.out.print(prompt);
      try {
        return Integer.parseInt(scanner.nextLine());
      } catch (NumberFormatException e) {
        System.out.println("Invalid input. Please enter an integer.");
      }
    }
  }

  /**
   * Reads a positive non-zero double from the user with a prompt.
   *
   * @param prompt the prompt to display to the user
   * @return the double entered by the user
   */
  private double readPositiveDouble(String prompt) {
    while (true) {
      System.out.print(prompt);
      try {
        double value = Double.parseDouble(scanner.nextLine());
        if (value == 0) {
          System.out.println("Value cannot be zero. Please enter a positive number.");
          continue;
        }
        if (value < 0) {
          System.out.println("Value cannot be negative. Please enter a positive number.");
          continue;
        }
        return value;
      } catch (NumberFormatException e) {
        System.out.println("Invalid input. Please enter a number.");
      }
    }
  }

  /**
   * Reads a valid measurement unit from the user.
   *
   * @return the unit entered by the user
   */
  private String readUnit() {
    while (true) {
      System.out.print("Enter unit: ");
      String unit = scanner.nextLine();
      try {
        MeasurementUnit.fromString(unit);
        return unit;
      } catch (IllegalArgumentException e) {
        System.out.println(
            "Invalid unit. Please enter a valid measurement unit (e.g., liter, kg, pcs).");
      }
    }
  }

  /**
   * Reads a valid date from the user.
   *
   * @return the date entered by the user
   */
  private LocalDate readDate() {
    while (true) {
      try {
        int year = readInt("Year: ");
        int month = readInt("Month: ");
        int day = readInt("Day: ");
        return LocalDate.of(year, month, day);
      } catch (DateTimeException e) {
        System.out.println("Invalid date. Please enter a valid date.");
      }
    }
  }

  /** Adds a grocery to storage with error handling. */
  private void addGrocery() {
    System.out.println("Adding grocery...");
    final String name = readName();

    final double amount = readPositiveDouble("Enter amount: ");
    final String unit = readUnit();

    System.out.println("Enter expiration date: ");
    final LocalDate expirationDate = readDate();

    final double price = readPositiveDouble("Enter price per unit (NOK): ");

    System.out.println();

    try {
      storageController.addGrocery(name, amount, unit, expirationDate, price);
      System.out.println("Grocery added successfully.");
    } catch (Exception e) {
      System.out.println("Error adding grocery: " + e.getMessage());
    }
  }

  /** Removes a grocery from storage with error handling. */
  private void removeGrocery() {
    System.out.print("Enter name of grocery to remove: ");
    String name = scanner.nextLine();

    if (name == null || name.isEmpty()) {
      System.out.println("Name cannot be empty. Please enter a name.");
      return;
    }

    if (!storageController.getGrocery(name)) {
      System.out.println("Grocery not found.");
      return;
    }

    try {
      storageController.removeGrocery(name);
      System.out.println("Grocery removed successfully.");
    } catch (Exception e) {
      System.out.println("Error removing grocery: " + e.getMessage());
    }
  }

  /** Lists groceries in storage. */
  private void listGroceries() {
    System.out.println("Listing groceries...");

    storageController.listAllGroceries();
  }

  /** Lists expired groceries in storage and the total value of these groceries. */
  private void listExpiredGroceries() {
    System.out.println("Listing expired groceries...");

    storageController.listExpiredGroceries();
    storageController.displayExpiredTotalValue();
  }

  /** Prints the total value of all groceries in storage. */
  private void calculateTotalValue() {
    storageController.displayTotalValue();
  }
}
