package edu.ntnu.idi.idatt.view;

import edu.ntnu.idi.idatt.model.Grocery;

/** Class representing the view for groceries. */
public class GroceryView {
  /**
   * Displays a grocery.
   *
   * @param grocery the grocery to display
   */
  public static void displayGrocery(Grocery grocery) {
    if (grocery == null) {
      System.out.println("Grocery not found");
      return;
    }
    System.out.println(grocery);
  }
}
