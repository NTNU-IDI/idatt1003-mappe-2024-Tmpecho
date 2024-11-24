package edu.ntnu.idi.idatt.controller;

import edu.ntnu.idi.idatt.repository.StorageRepository;
import java.time.LocalDate;

/** Controller class for the storage model. */
public class StorageController {
  private final GroceryController groceryController;
  private final StorageRepository storageRepository;

  /**
   * Constructor for the StorageController class.
   *
   * @param groceryController controller for the grocery model
   * @param storageRepository repository for the storage model
   */
  public StorageController(
      GroceryController groceryController, StorageRepository storageRepository) {
    this.groceryController = groceryController;
    this.storageRepository = storageRepository;
  }

  /**
   * Method for adding a grocery to the storage model.
   *
   * @param name the name of the grocery
   * @param amount the amount of the grocery
   * @param unit the unit of the grocery
   * @param expiration_date the expiration date of the grocery
   * @param price the price of the grocery
   */
  public void addGrocery(
      String name, double amount, String unit, LocalDate expiration_date, double price) {
    storageRepository.addGrocery(
        groceryController.addGrocery(name, amount, unit, expiration_date, price));
  }

  /**
   * Remove a grocery from the storage model.
   *
   * @param name the name of the grocery
   */
  public void removeGrocery(String name) {
    storageRepository.removeGrocery(name);
  }

  /** List all groceries in the storage model. */
  public void listAllGroceries() {
    if (storageRepository.getAllGroceries().isEmpty()) {
      System.out.println("No groceries in storage.");
      return;
    }
    storageRepository.getAllGroceries().forEach(groceryController::displayGrocery);
  }

  /** List all expired groceries in the storage model. */
  public void listExpiredGroceries() {
    if (storageRepository.listExpiredGroceries().isEmpty()) {
      System.out.println("No expired groceries in storage.");
      return;
    }
    storageRepository.listExpiredGroceries().forEach(groceryController::displayGrocery);
  }
}
