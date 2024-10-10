package edu.ntnu.idi.idatt.model;

public class Storage {
  String name;
  double capacity;
  double currentCapacity;
  public double minTemperature;
  public double maxTemperature;

  public Storage(String name, double capacity, double minTemperature, double maxTemperature) {
    this.name = name;
    this.capacity = capacity;
    this.minTemperature = minTemperature;
    this.maxTemperature = maxTemperature;
    this.currentCapacity = 0;
  }

  public void addGrocery(Grocery grocery) {
    if (currentCapacity + grocery.getAmount() > capacity) {
      throw new IllegalArgumentException("Not enough capacity in storage");
    }
    currentCapacity += grocery.getAmount();
  }

  public void removeGrocery(Grocery grocery, double amount) {
    double remainingAmount = grocery.getAmount() - amount;
    if (remainingAmount < 0) {
      throw new IllegalArgumentException("Removing more than available");
    }
    currentCapacity -= amount;
  }

  public double getCapacity() {
    return capacity;
  }

  public double getCurrentCapacity() {
    return currentCapacity;
  }

  public String getName() {
    return name;
  }

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
