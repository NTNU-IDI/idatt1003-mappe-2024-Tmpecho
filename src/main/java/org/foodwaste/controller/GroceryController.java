package org.foodwaste.controller;

import org.foodwaste.model.Grocery;
import org.foodwaste.repository.StorageRepository;
import org.foodwaste.view.StorageView;

public class GroceryController {
  private final StorageRepository storageRepository;
  private final StorageView view;

  public GroceryController(StorageRepository storageRepo, StorageView view) {
    this.storageRepository = storageRepo;
    this.view = view;
  }

  public void addGrocery(Grocery grocery) {
    storageRepository.addGrocery(grocery);
  }

  public Grocery getGrocery(String name) {
    return storageRepository.getGrocery(name);
  }

  public void removeGrocery(String name, float amount) {
    storageRepository.removeGrocery(name, amount);
  }

  public void listAllGroceries() {

  }
}
