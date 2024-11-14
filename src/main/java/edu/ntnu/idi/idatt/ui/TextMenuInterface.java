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
        case 1:
          addGrocery();
          break;
        case 2:
          listGroceries();
          break;
        case 3:
          listStorages();
          break;
        case 4:
          removeGrocery();
          break;
        case 5:
          moveGroceryToStorage();
          break;
        case 0:
          System.out.println("Exiting...");
          break;
        default:
          System.out.println("Invalid choice");
      }
    }
  }

  /** Prints the menu of choices for the user to perform. */
  public void printMenu() {
    System.out.println("\nMenu:");
    System.out.println("1. Add grocery");
    System.out.println("2. List groceries");
    System.out.println("3. List storages");
    System.out.println("4. Remove grocery");
    System.out.println("5. Move grocery to storage");
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
    String name = scanner.next();
    System.out.print("Enter amount: ");
    double amount = scanner.nextDouble();
    System.out.print("Enter unit: ");
    String unit = scanner.next();
    System.out.println("Enter expiration date: ");
    System.out.print("Year: ");
    int year = scanner.nextInt();
    System.out.print("Month: ");
    int month = scanner.nextInt();
    System.out.print("Day: ");
    int day = scanner.nextInt();
    System.out.println("Enter price (NOK): ");
    System.out.print("Price: ");
    double price = scanner.nextDouble();
    System.out.println();
    storageController.addGrocery(name, amount, unit, LocalDate.of(year, month, day), price);
  }

  /** Lists groceries in storage. */
  private void listGroceries() {
    System.out.println("Listing groceries...");

    storageController.listAllGroceries();
  }

  /** Lists storages. */
  private void listStorages() {
    System.out.println("Listing storages...");
  }

  /** Removes a grocery from storage. */
  private void removeGrocery() {
    System.out.println("Removing grocery...");
  }

  /** Moves a grocery to a specific storage. */
  private void moveGroceryToStorage() {
    System.out.println("Moving grocery to storage...");
  }
}
