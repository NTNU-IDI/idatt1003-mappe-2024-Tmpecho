package edu.ntnu.idi.idatt.controller;

import edu.ntnu.idi.idatt.model.Grocery;
import edu.ntnu.idi.idatt.model.Storage;
import edu.ntnu.idi.idatt.repository.StorageRepository;
import edu.ntnu.idi.idatt.view.StorageView;
import java.time.LocalDate;
import java.util.List;

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
   * @return a list of groceries with their amount
   */
  public List<Grocery> getAllGroceries() {
    if (storageRepository.getAllGroceries().isEmpty()) {
      return List.of();
    } else {
      List<Grocery> groceries = storageRepository.getAllGroceries();
      return storageRepository.getAllGroceries();
    }
  }

  /**
   * List all groceries in the storage model.
   *
   * @return {@code true} if there are groceries in the storage model, {@code false} otherwise.
   */
  public boolean listAllGroceries() {
    if (storageRepository.getAllGroceries().isEmpty()) {
      return false;
    }
    StorageView.displayStorage(storageRepository.getAllGroceries());
    return true;
  }

  /**
   * List all expired groceries in the storage model.
   *
   * @return {@code true} if there are expired groceries in the storage model, {@code false}
   *     otherwise.
   */
  public boolean listExpiredGroceries() {
    if (storageRepository.listExpiredGroceries().isEmpty()) {
      return false;
    }
    StorageView.displayStorage(storageRepository.listExpiredGroceries());
    return true;
  }

  /** Display the total value of all groceries in the storage model. */
  public void displayTotalValue() {
    StorageView.displayTotalValue(storageRepository.calculateTotalValue());
  }

  /** Display the total value of all expired groceries in the storage model. */
  public void displayExpiredTotalValue() {
    double totalExpiredValue =
        storageRepository.listExpiredGroceries().stream()
            .mapToDouble(GroceryController::calculateGroceryValue)
            .sum();

    StorageView.displayExpiredValue(totalExpiredValue);
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

  /**
   * Display storage details.
   *
   * @param storage The storage to display details for.
   */
  public void displayStorageDetails(Storage storage) {
    StorageView.displayStorageDetails(storage);
  }
}
