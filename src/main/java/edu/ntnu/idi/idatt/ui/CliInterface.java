package edu.ntnu.idi.idatt.ui;


/** CLI implementation of the UserInterface. */
public class CliInterface extends UserInterface {
  public void start() {
    super.start();

    System.out.println("CLI spesicifc start...");

    groceryController.addGrocery("Milk", 3, "Liter");
    groceryController.displayGrocery("Milk");

    groceryController.removeGrocery("Milk", 1);
    groceryController.displayGrocery("Milk");

    groceryController.removeGrocery("Milk");
    groceryController.displayGrocery("Milk");
  }
}
