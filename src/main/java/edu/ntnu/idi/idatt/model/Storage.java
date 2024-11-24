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
   * @param name name of storage
   * @param capacity capacity of storage
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
   * @param grocery grocery to add
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
   * @param grocery grocery to remove
   * @param amount amount to remove
   */
  public void removeGrocery(Grocery grocery, double amount) {
    double remainingAmount = grocery.getAmount() - amount;
    if (remainingAmount < 0) {
      throw new IllegalArgumentException("Removing more than available");
    }
    currentCapacity -= amount;
  }

  /**
   * Get the capacity of the storage.
   *
   * @return capacity of the storage
   */
  public double getCapacity() {
    return capacity;
  }

  /**
   * Get the current capacity of the storage.
   *
   * @return current capacity of the storage
   */
  public double getCurrentCapacity() {
    return currentCapacity;
  }

  /**
   * Get the name of the storage.
   *
   * @return name of the storage
   */
  public String getName() {
    return name;
  }

  /**
   * Get string representation of storage.
   *
   * @return string representation of storage
   */
  @Override
  public String toString() {
    return "Storage{"
        + "name='"
        + name
        + '\''
        + ", capacity="
        + capacity
        + ", currentCapacity="
        + currentCapacity
        + ", minTemperature="
        + minTemperature
        + ", maxTemperature="
        + maxTemperature
        + '}';
  }
}
