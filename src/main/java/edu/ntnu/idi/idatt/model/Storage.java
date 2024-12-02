package edu.ntnu.idi.idatt.model;

/** Class representing a storage unit for groceries. */
public class Storage {
  public double minTemperature;
  public double maxTemperature;
  String name;
  double capacity;
  double currentCapacity;

  /**
   * Constructor for Storage.
   *
   * @param name The name of the storage
   * @param capacity The capacity of the storage
   */
  public Storage(String name, double capacity, double minTemperature, double maxTemperature) {
    this.name = name;
    this.capacity = capacity;
    this.minTemperature = minTemperature;
    this.maxTemperature = maxTemperature;
    this.currentCapacity = 0;
  }

  /**
   * Add grocery to storage.
   *
   * @param grocery The grocery to add
   */
  public void addGrocery(Grocery grocery) {
    if (currentCapacity + grocery.getAmount() > capacity) {
      throw new IllegalArgumentException("Not enough capacity in storage");
    }
    currentCapacity += grocery.getAmount();
  }

  /**
   * Remove grocery from storage.
   *
   * @param grocery The grocery to remove
   */
  public void removeGrocery(Grocery grocery) {
    removeGrocery(grocery, grocery.getAmount());
  }

  /**
   * Remove an amount of a grocery from storage.
   *
   * @param grocery The grocery to remove
   * @param amount The amount to remove
   */
  public void removeGrocery(Grocery grocery, double amount) {
    if (grocery.getAmount() == null) {
      throw new IllegalArgumentException("Grocery amount cannot be null");
    }
    double remainingAmount = grocery.getAmount() - amount;
    if (remainingAmount < 0) {
      throw new IllegalArgumentException("Removing more than available");
    }
    if (amount <= 0) {
      throw new IllegalArgumentException("Amount to remove cannot be negative or zero");
    }

    currentCapacity -= amount;
  }

  /**
   * Get the capacity of the storage.
   *
   * @return The capacity of the storage
   */
  public double getCapacity() {
    return capacity;
  }

  /**
   * Get the current capacity of the storage.
   *
   * @return The current capacity of the storage
   */
  public double getCurrentCapacity() {
    return currentCapacity;
  }

  /**
   * Get the name of the storage.
   *
   * @return The name of the storage
   */
  public String getName() {
    return name;
  }
}
