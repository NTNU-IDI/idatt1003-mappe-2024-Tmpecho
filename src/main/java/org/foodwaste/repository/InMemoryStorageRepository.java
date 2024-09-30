package org.foodwaste.repository;

import java.util.ArrayList;
import java.util.List;
import org.foodwaste.model.Grocery;

public class InMemoryStorageRepository implements StorageRepository {
  private final List<Grocery> groceries = new ArrayList<>();

  @Override
  public void addGrocery(Grocery grocery) {
    groceries.add(grocery);
  }

  @Override
  public Grocery getGrocery(String name) {
    return groceries.stream()
        .filter(grocery -> grocery.getName().equals(name))
        .findFirst()
        .orElse(null);
  }

  @Override
  public void removeGrocery(String name, float amount) {
    Grocery grocery = getGrocery(name);
    if (grocery != null) {
      float newAmount = grocery.getAmount() - amount;
      if (newAmount <= 0) {
        groceries.remove(grocery);
      } else {
        grocery.setAmount(newAmount);
      }
    }
  }

  @Override
  public List<Grocery> listAllGroceries() {
    return new ArrayList<>(groceries); // Return a copy to prevent external modification
  }

  @Override
  public List<Grocery> listExpiredGroceries() {
    return groceries.stream()
        .filter(
            grocery ->
                grocery.getExpirationDate() != null
                    && grocery.getExpirationDate().isBefore(java.time.LocalDate.now()))
        .toList();
  }

  @Override
  public float calculateTotalValue() {
    return groceries.stream()
        .map(grocery -> grocery.getPrice() != null ? grocery.getPrice() * grocery.getAmount() : 0f)
        .reduce(0f, Float::sum);
  }
}
