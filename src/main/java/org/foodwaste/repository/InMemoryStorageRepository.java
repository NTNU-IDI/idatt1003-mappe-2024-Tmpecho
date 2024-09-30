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
    for (Grocery grocery : groceries) {
      if (grocery.getName().equals(name)) {
        return grocery;
      }
    }
    return null;
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
    List<Grocery> expiredGroceries = new ArrayList<>();
    for (Grocery grocery : groceries) {
      if (grocery.getExpirationDate() != null
          && grocery.getExpirationDate().isBefore(java.time.LocalDate.now())) {
        expiredGroceries.add(grocery);
      }
    }
    return expiredGroceries;
  }

  @Override
  public float calculateTotalValue() {
    float totalValue = 0f;
    for (Grocery grocery : groceries) {
      if (grocery.getPrice() != null) {
        totalValue += grocery.getPrice() * grocery.getAmount();
      }
    }
    return totalValue;
  }
}
