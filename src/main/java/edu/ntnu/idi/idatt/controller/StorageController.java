package edu.ntnu.idi.idatt.controller;

import edu.ntnu.idi.idatt.model.Grocery;
import edu.ntnu.idi.idatt.repository.StorageRepository;
import edu.ntnu.idi.idatt.view.StorageView;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

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
   * @param expirationDate the expiration date of the grocery
   * @param price the price of the grocery
   */
  public void addGrocery(
      String name, double amount, String unit, LocalDate expirationDate, double price) {
    storageRepository.addGrocery(
        groceryController.createGrocery(name, amount, unit, expirationDate, price));
  }

  /**
   * Remove a grocery from the storage model.
   *
   * @param name the name of the grocery
   */
  public void removeGrocery(String name) {
    storageRepository.removeGrocery(name);
  }

  /**
   * Get all groceries with their amount.
   *
   * @return a map of groceries with their amount
   */
  public Map<Grocery, Double> getAllGroceriesWithAmount() {
    if (storageRepository.getAllGroceries().isEmpty()) {
      return Map.of();
    } else {
      List<Grocery> groceries = storageRepository.getAllGroceries();
      return storageRepository.getAllGroceriesWithAmount(groceries);
    }
  }

  /** List all groceries in the storage model. */
  public void listAllGroceries() {
    if (storageRepository.getAllGroceries().isEmpty()) {
      System.out.println("No groceries in storage.");
      return;
    }
    StorageView.displayStorage(storageRepository.getAllGroceries());
  }

  /** List all expired groceries in the storage model. */
  public void listExpiredGroceries() {
    if (storageRepository.listExpiredGroceries().isEmpty()) {
      System.out.println("No expired groceries in storage.");
      return;
    }
    StorageView.displayStorage(storageRepository.listExpiredGroceries());
  }

  /** Display the total value of all groceries in the storage model. */
  public void displayTotalValue() {
    System.out.println("Total value: " + storageRepository.calculateTotalValue() + " NOK");
  }

  /** Display the total value of all expired groceries in the storage model. */
  public void displayExpiredTotalValue() {
    double totalExpiredValue =
        storageRepository.listExpiredGroceries().stream()
            .mapToDouble(GroceryController::calculateGroceryValue)
            .sum();

    System.out.println("Total value of expired groceries: " + totalExpiredValue + " NOK");
  }

  /**
   * Checks if a grocery is in storage.
   *
   * @param name the name of the grocery to get
   * @return true if the grocery is in storage, false otherwise
   */
  public boolean groceryInStorage(String name) {
    return storageRepository.getGrocery(name) != null;
  }
}
