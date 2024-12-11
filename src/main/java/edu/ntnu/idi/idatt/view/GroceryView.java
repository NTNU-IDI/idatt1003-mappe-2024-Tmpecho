package edu.ntnu.idi.idatt.view;

import edu.ntnu.idi.idatt.controller.GroceryController;
import edu.ntnu.idi.idatt.model.Grocery;
import edu.ntnu.idi.idatt.util.DateUtils;

/**
 * Class representing the view for groceries.
 *
 * <p>Contains methods for displaying groceries in different ways. This class is used by the {@link
 * GroceryController} to display groceries to the user.
 *
 * @see Grocery
 * @see GroceryController
 * @see StorageView
 */
public class GroceryView {
  /**
   * Displays a grocery as part of a table row.
   *
   * @param grocery The grocery to display
   */
  public static void displayGroceryAsRow(Grocery grocery) {
    if (grocery == null) {
      System.out.println("Grocery not found");
      return;
    }

    System.out.printf(
        "%-15s %-10.2f %-10s %-15s %-10.2f%n",
        grocery.getName(),
        grocery.getAmount(),
        grocery.getMeasurementUnit(),
        grocery.getExpirationDate().format(DateUtils.FORMATTER),
        grocery.getPrice());
  }
}
