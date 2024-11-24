package edu.ntnu.idi.idatt.controller;

import edu.ntnu.idi.idatt.model.Grocery;
import edu.ntnu.idi.idatt.model.MeasurementUnit;
import edu.ntnu.idi.idatt.view.GroceryView;
import java.time.LocalDate;

/** Controller class for the grocery model. */
public class GroceryController {
  private final GroceryView groceryView = new GroceryView();

  public GroceryController() {}

  /**
   * Add a grocery to the model.
   *
   * @param name the name of the grocery
   * @param amount the amount of the grocery
   * @param unit the unit of the grocery
   * @param expirationDate the expiration date of the grocery
   * @param price the price of the grocery
   * @return the grocery that was added
   */
  public Grocery addGrocery(
      String name, double amount, String unit, LocalDate expirationDate, Double price) {
    return new Grocery.Builder(
            name.toUpperCase(), amount, MeasurementUnit.valueOf(unit.toUpperCase()))
        .expirationDate(expirationDate)
        .price(price)
        .build();
  }

  /**
   * Remove a grocery from the model.
   *
   * @param name the name of the grocery
   */
  public void removeGrocery(String name) {}

  /**
   * Remove a grocery from the model.
   *
   * @param name the name of the grocery
   * @param amount the amount of the grocery
   */
  public void removeGrocery(String name, double amount) {}

  /**
   * Display a grocery from the model.
   *
   * @param grocery the grocery to display
   */
  public void displayGrocery(Grocery grocery) {
    groceryView.displayGrocery(grocery);
  }
}
