package edu.ntnu.idi.idatt.repository;

import edu.ntnu.idi.idatt.model.Grocery;
import java.util.List;
import java.util.Map;

/** Interface for the storage repository. */
public interface StorageRepository {
  /**
   * Adds a grocery to the storage.
   *
   * @param grocery the grocery to add
   */
  void addGrocery(Grocery grocery);

  /**
   * Gets a grocery from the storage.
   *
   * @param name the name of the grocery to get
   * @return the grocery with the given name
   */
  Grocery getGrocery(String name);

  /**
   * Removes a grocery from the storage.
   *
   * @param name the name of the grocery to remove
   */
  void removeGrocery(String name);

  /**
   * Removes a specific amount of a grocery from the storage.
   *
   * @param name the name of the grocery to remove
   * @param amount the amount to remove
   */
  void removeGrocery(String name, double amount);

  /**
   * Lists all groceries in the storage.
   *
   * @return a list of all groceries in the storage
   */
  List<Grocery> getAllGroceries();

  /**
   * Lists all expired groceries in the storage.
   *
   * @return a list of all expired groceries in the storage
   */
  List<Grocery> listExpiredGroceries();

  /**
   * Calculates the total value of all groceries in the storage.
   *
   * @return the total value of all groceries in the storage
   */
  double calculateTotalValue();

  /**
   * Gets all groceries with their amount.
   *
   * @return a map of groceries with their amount
   */
  Map<Grocery, Double> getAllGroceriesWithAmount();
}
