package edu.ntnu.idi.idatt.controller;

import edu.ntnu.idi.idatt.model.Grocery;
import edu.ntnu.idi.idatt.model.MeasurementUnit;
import edu.ntnu.idi.idatt.view.GroceryView;
import java.time.LocalDate;

/** Controller class for the grocery model. */
public class GroceryController {
  private final GroceryView groceryView = new GroceryView();

  /**
   * Create a grocery object.
   *
   * @param name the name of the grocery
   * @param amount the amount of the grocery
   * @param unit the unit of the grocery
   * @param expirationDate the expiration date of the grocery
   * @param price the price of the grocery
   * @return the grocery that was added
   */
  public Grocery createGrocery(
      String name, double amount, String unit, LocalDate expirationDate, Double price) {
    return new Grocery(name, amount, MeasurementUnit.fromString(unit), expirationDate, price);
  }

  /**
   * Display a grocery from the model.
   *
   * @param grocery the grocery to display
   */
  public void displayGrocery(Grocery grocery) {
    groceryView.displayGrocery(grocery);
  }

  /**
   * Calculates the total value of a grocery.
   *
   * @param grocery The grocery whose value is to be calculated.
   * @return The total value of the grocery (price * amount).
   */
  public double calculateGroceryValue(Grocery grocery) {
    if (grocery == null || grocery.getPrice() == null || grocery.getAmount() == null) {
      return 0.0; // Default to 0 if price or amount is null
    }
    return grocery.getPrice() * grocery.getAmount();
  }
}
