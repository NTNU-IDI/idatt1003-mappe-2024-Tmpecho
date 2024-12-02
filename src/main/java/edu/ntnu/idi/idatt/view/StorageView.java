package edu.ntnu.idi.idatt.view;

import edu.ntnu.idi.idatt.model.Grocery;
import java.util.List;

/** Class representing the view for storage. */
public class StorageView {
  /**
   * Displays all groceries in the storage.
   *
   * @param groceries the groceries to display
   */
  public static void displayStorage(List<Grocery> groceries) {
    groceries.forEach(System.out::println);
  }
}
