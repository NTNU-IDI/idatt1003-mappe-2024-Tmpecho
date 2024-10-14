package edu.ntnu.idi.idatt.view;

import edu.ntnu.idi.idatt.model.Grocery;

public class GroceryView {
  public void displayGrocery(Grocery grocery) {
    if (grocery == null) {
      System.out.println("Grocery not found");
      return;
    }
    System.out.println(grocery);
  }
}
