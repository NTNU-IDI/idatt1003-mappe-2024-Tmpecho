package org.foodwaste.controller;

import java.util.List;
import org.foodwaste.model.*;
import org.foodwaste.model.repositories.StorageRepository;

public class GroceryController {
  private final StorageRepository groceryRepository;

  public GroceryController(StorageRepository storageRepo) {
    this.groceryRepository = storageRepo;
  }

  public void addGrocery(Grocery grocery) {
    groceryRepository.addGrocery(grocery);
  }

  public Grocery getGrocery(String name) {
    return groceryRepository.getGrocery(name);
  }

  public void removeGrocery(String name, float amount) {
    groceryRepository.removeGrocery(name, amount);
  }

  public List<Grocery> listAllGroceries() {
    return groceryRepository.listAllGroceries();
  }

  public List<Grocery> listExpiredGroceries() {
    return groceryRepository.listExpiredGroceries();
  }

  public float calculateTotalValue() {
    return groceryRepository.calculateTotalValue();
  }
}
