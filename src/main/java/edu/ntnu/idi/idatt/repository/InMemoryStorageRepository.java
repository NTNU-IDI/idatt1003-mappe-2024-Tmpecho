package edu.ntnu.idi.idatt.repository;

import edu.ntnu.idi.idatt.model.Grocery;
import edu.ntnu.idi.idatt.model.Storage;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/** A repository for storing groceries in memory. */
public class InMemoryStorageRepository implements StorageRepository {
  private final Storage storage;
  private final List<Grocery> groceries = new ArrayList<>();

  /**
   * Creates a new instance of the repository.
   *
   * @param storage the storage to use
   */
  public InMemoryStorageRepository(Storage storage) {
    this.storage = storage;
  }

  /**
   * Adds a grocery to the repository.
   *
   * @param grocery the grocery to add
   */
  @Override
  public void addGrocery(Grocery grocery) {
    groceries.add(grocery);
    storage.addGrocery(grocery);
  }

  /**
   * Get a grocery by name.
   *
   * @param name the name of the grocery to get
   * @return the grocery with the given name, or null if no such grocery exists
   */
  @Override
  public Grocery getGrocery(String name) {
    for (Grocery grocery : groceries) {
      if (grocery.getName().equals(name)) {
        return grocery;
      }
    }
    return null;
  }

  /**
   * Remove a grocery by name.
   *
   * @param name the name of the grocery to remove
   */
  @Override
  public void removeGrocery(String name) {
    Grocery grocery = getGrocery(name);
    if (grocery != null) {
      storage.removeGrocery(grocery, grocery.getAmount());
      groceries.remove(grocery);
    }
  }

  /**
   * Remove a certain amount of a grocery by name.
   *
   * @param name the name of the grocery to remove
   * @param amount the amount to remove
   */
  @Override
  public void removeGrocery(String name, double amount) {
    if (amount < 0) {
      throw new IllegalArgumentException("Amount cannot be negative");
    }
    Grocery grocery = getGrocery(name);

    if (grocery.getAmount() - amount < 0) {
      throw new IllegalArgumentException("Amount cannot be negative");
    }

    if (grocery.getAmount() - amount == 0) {
      removeGrocery(name);
      return;
    }

    storage.removeGrocery(grocery, amount);
    grocery.setAmount(grocery.getAmount() - amount);
  }

  /**
   * List all groceries in the repository.
   *
   * @return a list of all groceries in the repository
   */
  @Override
  public List<Grocery> listAllGroceries() {
    List<Grocery> sortedGroceries = new ArrayList<>(groceries);
    sortedGroceries.sort(Comparator.comparing(Grocery::getName));
    return sortedGroceries;
  }

  /**
   * List all expired groceries in the repository.
   *
   * @return a list of all expired groceries in the repository
   */
  @Override
  public List<Grocery> listExpiredGroceries() {
    List<Grocery> expiredGroceries = new ArrayList<>();
    for (Grocery grocery : groceries) {
      if (grocery.getExpirationDate() != null
          && grocery.getExpirationDate().isBefore(java.time.LocalDate.now())) {
        expiredGroceries.add(grocery);
      }
    }
    return expiredGroceries;
  }

  /**
   * Calculate the total value of all groceries in the repository.
   *
   * @return the total value of all groceries in the repository
   */
  @Override
  public double calculateTotalValue() {
    double totalValue = 0.0;
    for (Grocery grocery : groceries) {
      if (grocery.getPrice() != null) {
        totalValue += grocery.getPrice() * grocery.getAmount();
      }
    }
    return totalValue;
  }
}
