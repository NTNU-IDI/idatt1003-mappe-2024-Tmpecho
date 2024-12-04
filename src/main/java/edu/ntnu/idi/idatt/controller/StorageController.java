package edu.ntnu.idi.idatt.controller;

import edu.ntnu.idi.idatt.model.Grocery;
import edu.ntnu.idi.idatt.model.Storage;
import edu.ntnu.idi.idatt.repository.StorageRepository;
import edu.ntnu.idi.idatt.view.StorageView;
import java.time.LocalDate;
import java.util.List;

/**
 * Controller class for the storage model.
 *
 * <p>Speaks with {@link GroceryController} and {@link StorageRepository} to manage storage.
 */
public class StorageController {
  private final GroceryController groceryController;
  private final StorageRepository storageRepository;

  /**
   * Constructor for the StorageController class.
   *
   * @param groceryController Controller for the grocery model
   * @param storageRepository Repository for the storage model
   */
  public StorageController(
      GroceryController groceryController, StorageRepository storageRepository) {
    this.groceryController = groceryController;
    this.storageRepository = storageRepository;
  }

  /**
   * Method for adding a grocery to the storage model.
   *
   * @param name The name of the grocery
   * @param amount The amount of the grocery
   * @param unit The unit of the grocery
   * @param expirationDate The expiration date of the grocery
   * @param price The price of the grocery
   */
  public void addGrocery(
      String name, double amount, String unit, LocalDate expirationDate, double price) {
    storageRepository.addGrocery(
        groceryController.createGrocery(name, amount, unit, expirationDate, price));
  }

  /**
   * Remove a grocery from the storage model.
   *
   * @param name The name of the grocery
   */
  public void removeGrocery(String name) {
    storageRepository.removeGrocery(name);
  }

  /**
   * Get all groceries with their amount.
   *
   * @return A list of groceries with their amount
   */
  public List<Grocery> getAllGroceries() {
    if (storageRepository.getAllGroceries().isEmpty()) {
      return List.of();
    } else {
      List<Grocery> groceries = storageRepository.getAllGroceries();
      return storageRepository.getAllGroceries();
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
   * @param name The name of the grocery to get
   * @return True if the grocery is in storage, false otherwise
   */
  public boolean groceryInStorage(String name) {
    return storageRepository.getGrocery(name) != null;
  }

  /**
   * Display storage details.
   *
   * @param storage The storage to display details for
   */
  public void displayStorageDetails(Storage storage) {
    StorageView.displayStorageDetails(storage);
  }
}
