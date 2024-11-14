package edu.ntnu.idi.idatt.controller;

import edu.ntnu.idi.idatt.model.Grocery;
import edu.ntnu.idi.idatt.model.MeasurementUnit;
import edu.ntnu.idi.idatt.view.GroceryView;

import java.time.LocalDate;

public class GroceryController {
  private final GroceryView groceryView = new GroceryView();

  public GroceryController() {}

  public Grocery addGrocery(
      String name, double amount, String unit, LocalDate expirationDate, Double price) {
    return new Grocery.Builder(name.toUpperCase(), amount, MeasurementUnit.valueOf(unit.toUpperCase()))
        .expirationDate(expirationDate)
        .price(price)
        .build();
  }

  public void removeGrocery(String name) {}

  public void removeGrocery(String name, double amount) {}

  public void displayGrocery(String name) {}
}
