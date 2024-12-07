package edu.ntnu.idi.idatt.view;

import edu.ntnu.idi.idatt.model.Grocery;
import edu.ntnu.idi.idatt.model.Storage;
import java.util.List;

/** Class representing the view for storage. */
public class StorageView {
  /**
   * Display storage details.
   *
   * @param storage The storage to display.
   */
  public static void displayStorageDetails(Storage storage) {
    System.out.println("Storage details:");
    System.out.println("Name: " + storage.getName());
    System.out.println("Capacity: " + storage.getCurrentCapacity() + "/" + storage.getCapacity());
  }

  /**
   * Displays all groceries in the storage as a table.
   *
   * @param groceries The groceries to display
   */
  public static void displayStorage(List<Grocery> groceries) {
    if (groceries == null || groceries.isEmpty()) {
      System.out.println("No groceries found.");
      return;
    }

    System.out.printf(
        "%-15s %-10s %-10s %-15s %-10s%n",
        "Name", "Amount", "Unit", "Expiration Date", "Price (NOK)");
    System.out.println("-------------------------------------------------------------");

    groceries.forEach(GroceryView::displayGroceryAsRow);
  }

  /**
   * Displays the total value of all groceries in the storage.
   *
   * @param totalValue The total value of all groceries in the storage.
   */
  public static void displayTotalValue(double totalValue) {
    System.out.println("Total value: " + totalValue + " NOK");
  }

  /**
   * Displays the total value of all expired groceries in the storage.
   *
   * @param totalExpiredValue The total value of all expired groceries in the storage.
   */
  public static void displayExpiredValue(double totalExpiredValue) {
    System.out.println("Total value of expired groceries: " + totalExpiredValue + " NOK");
  }
}
