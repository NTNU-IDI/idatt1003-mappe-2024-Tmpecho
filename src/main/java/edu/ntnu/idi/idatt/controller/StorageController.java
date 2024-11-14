package edu.ntnu.idi.idatt.controller;

import edu.ntnu.idi.idatt.model.Grocery;
import edu.ntnu.idi.idatt.repository.StorageRepository;

import java.time.LocalDate;

public class StorageController {
  private GroceryController groceryController;
    private StorageRepository storageRepository;

  public StorageController(GroceryController groceryController, StorageRepository storageRepository) {
    this.groceryController = groceryController;
    this.storageRepository = storageRepository;
  }

  public void addGrocery(String name, double amount, String unit, LocalDate expiration_date, double price) {
    storageRepository.addGrocery(groceryController.addGrocery(name, amount, unit, expiration_date, price));
  }

  public void listAllGroceries() {
    for (Grocery grocery : storageRepository.listAllGroceries()) {
      System.out.println(grocery);
    }
  }
}
