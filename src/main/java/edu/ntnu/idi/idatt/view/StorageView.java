package edu.ntnu.idi.idatt.view;

import edu.ntnu.idi.idatt.model.Grocery;
import java.util.List;

/** Class representing the view for storage. */
public class StorageView {
  /**
   * Displays all groceries in the storage as a table.
   *
   * @param groceries the groceries to display
   */
  public static void displayStorage(List<Grocery> groceries) {
    if (groceries == null || groceries.isEmpty()) {
      System.out.println("No groceries found.");
      return;
    }

    // Print table header
    System.out.printf(
        "%-15s %-10s %-10s %-15s %-10s%n",
        "Name", "Amount", "Unit", "Expiration Date", "Price (NOK)");
    System.out.println("-------------------------------------------------------------");

    // Print each grocery as a table row
    groceries.forEach(GroceryView::displayGroceryAsRow);
  }
}
