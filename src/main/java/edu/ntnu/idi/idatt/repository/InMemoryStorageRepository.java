package edu.ntnu.idi.idatt.repository;

import edu.ntnu.idi.idatt.model.Grocery;
import edu.ntnu.idi.idatt.model.Storage;
import java.util.ArrayList;
import java.util.List;

public class InMemoryStorageRepository implements StorageRepository {
  private final Storage storage;
  private final List<Grocery> groceries = new ArrayList<>();

  public InMemoryStorageRepository(Storage storage) {
    this.storage = storage;
  }

  @Override
  public void addGrocery(Grocery grocery) {
    groceries.add(grocery);
    storage.addGrocery(grocery);
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
  public void removeGrocery(String name) {
    Grocery grocery = getGrocery(name);
    if (grocery != null) {
      storage.removeGrocery(grocery, grocery.getAmount());
      groceries.remove(grocery);
    }
  }

  @Override
  public void removeGrocery(String name, double amount) {
    Grocery grocery = getGrocery(name);
    if (grocery != null) {
      storage.removeGrocery(grocery, amount);
        grocery.setAmount(grocery.getAmount() - amount);
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
