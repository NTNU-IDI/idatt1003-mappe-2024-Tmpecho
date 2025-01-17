package edu.ntnu.idi.idatt.repository;

import edu.ntnu.idi.idatt.model.Grocery;
import edu.ntnu.idi.idatt.model.Storage;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/** An in-memory implementation using an ArrayList of the {@link StorageRepository}. */
public class InMemoryStorageRepository implements StorageRepository {
  private final Storage storage;
  private final List<Grocery> groceries = new ArrayList<>();

  /**
   * Creates a new instance of the repository.
   *
   * @param storage The storage to use
   */
  public InMemoryStorageRepository(Storage storage) {
    this.storage = storage;
  }

  /**
   * Adds a grocery to the repository.
   *
   * @param grocery The grocery to add
   */
  @Override
  public void addGrocery(Grocery grocery) {
    groceries.add(grocery);
    storage.addGrocery(grocery);
  }

  /**
   * Get a grocery by name.
   *
   * @param name The name of the grocery to get
   * @return The grocery with the given name, or null if no such grocery exists
   */
  @Override
  public Grocery getGrocery(String name) {
    return groceries.stream()
        .filter(grocery -> grocery.getName().equals(name))
        .findFirst()
        .orElse(null);
  }

  /**
   * Remove a grocery by name.
   *
   * @param name The name of the grocery to remove
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
   * @param name The name of the grocery to remove
   * @param amount The amount to remove
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
   * @return A list of all groceries in the repository
   */
  @Override
  public List<Grocery> getAllGroceries() {
    List<Grocery> sortedGroceries = new ArrayList<>(groceries);
    sortedGroceries.sort(Comparator.comparing(Grocery::getName));
    return sortedGroceries;
  }

  /**
   * List all expired groceries in the repository.
   *
   * @return A list of all expired groceries in the repository
   */
  @Override
  public List<Grocery> listExpiredGroceries() {
    return groceries.stream()
        .filter(
            grocery ->
                grocery.getExpirationDate() != null
                    && grocery.getExpirationDate().isBefore(LocalDate.now()))
        .collect(Collectors.toList());
  }

  /**
   * Calculate the total value of all groceries in the repository.
   *
   * @return The total value of all groceries in the repository
   */
  @Override
  public double calculateTotalValue() {
    return groceries.stream()
        .filter(grocery -> grocery.getPrice() != null)
        .mapToDouble(grocery -> grocery.getPrice() * grocery.getAmount())
        .sum();
  }
}
