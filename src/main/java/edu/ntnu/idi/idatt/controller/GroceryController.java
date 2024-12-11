package edu.ntnu.idi.idatt.controller;

import edu.ntnu.idi.idatt.model.Grocery;
import edu.ntnu.idi.idatt.model.MeasurementUnit;
import edu.ntnu.idi.idatt.view.GroceryView;
import java.time.LocalDate;

/**
 * Controller class for the grocery model.
 *
 * <p>Speaks with {@link Grocery} and {@link GroceryView} to manage groceries.
 */
public class GroceryController {
  /**
   * Calculates the total value of a grocery.
   *
   * @param grocery The grocery whose value is to be calculated
   * @return The total value of the grocery (price * amount)
   */
  public static double calculateGroceryValue(Grocery grocery) {
    if (grocery == null || grocery.getPrice() == null || grocery.getAmount() == null) {
      return 0.0; // Default to 0 if price or amount is null
    }
    return grocery.getPrice() * grocery.getAmount();
  }

  /**
   * Create a grocery object.
   *
   * @param name The name of the grocery
   * @param amount The amount of the grocery
   * @param unit The unit of the grocery
   * @param expirationDate The expiration date of the grocery
   * @param price The price of the grocery
   * @return The grocery that was added
   */
  public Grocery createGrocery(
      String name, double amount, String unit, LocalDate expirationDate, Double price) {
    return new Grocery(name, amount, MeasurementUnit.fromString(unit), expirationDate, price);
  }
}
