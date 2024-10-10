package edu.ntnu.idi.idatt.controller;

import edu.ntnu.idi.idatt.model.Grocery;
import edu.ntnu.idi.idatt.model.MeasurementUnit;
import edu.ntnu.idi.idatt.repository.StorageRepository;
import edu.ntnu.idi.idatt.view.GroceryView;

public class GroceryController {
  private final GroceryView groceryView = new GroceryView();
  private final StorageRepository storageRepository;

  public GroceryController(StorageRepository storageRepository) {
    this.storageRepository = storageRepository;
  }

  public void addGrocery(String name, double amount, String unit) {
    MeasurementUnit measurementUnit = MeasurementUnit.valueOf(unit.toUpperCase());
    storageRepository.addGrocery(new Grocery.Builder(name, amount, measurementUnit).build());
  }

  public void removeGrocery(String name) {
    storageRepository.removeGrocery(name);
  }

  public void removeGrocery(String name, double amount) {
    storageRepository.removeGrocery(name, amount);
  }

  public void displayGrocery(String name) {
    groceryView.displayGrocery(storageRepository.getGrocery(name));
  }
}