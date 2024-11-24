package edu.ntnu.idi.idatt.ui;

import java.time.LocalDate;
import java.util.Scanner;

/** Text based menu interface for the user. */
public class TextMenuInterface extends UserInterface {
  Scanner scanner = new Scanner(System.in);

  /** Starts the text based menu interface. */
  public void start() {
    int choice = -1;
    while (choice != 0) {
      printMenu();
      choice = readInt();
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
   * Reads an integer from the user.
   *
   * @return The integer read from the user.
   */
  private int readInt() {
    System.out.print("Enter choice: ");
    while (!scanner.hasNextInt()) {
      System.out.println("Invalid input. Please enter a number.");
      System.out.print("Enter choice: ");
      scanner.nextLine();
    }
    return scanner.nextInt();
  }

  /** Adds a grocery to storage. */
  private void addGrocery() {
    System.out.println("Adding grocery...");
    System.out.print("Enter name: ");
    final String name = scanner.next();

    System.out.print("Enter amount: ");
    final double amount = scanner.nextDouble();

    System.out.print("Enter unit: ");
    final String unit = scanner.next();

    System.out.println("Enter expiration date: ");
    System.out.print("Year: ");
    final int year = scanner.nextInt();
    System.out.print("Month: ");
    final int month = scanner.nextInt();
    System.out.print("Day: ");
    final int day = scanner.nextInt();

    System.out.println("Enter price per unit (NOK): ");
    System.out.print("Price: ");
    final double price = scanner.nextDouble();

    System.out.println();

    storageController.addGrocery(name, amount, unit, LocalDate.of(year, month, day), price);
  }

  /** Removes a grocery from storage. */
  private void removeGrocery() {
    System.out.println("Removing grocery...");
    System.out.print("Enter name: ");
    String name = scanner.next();

    storageController.removeGrocery(name);
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
    System.out.println("Calculating total value...");

    storageController.displayTotalValue();
  }
}
