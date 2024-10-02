package org.foodwaste.model;

public class Storage {
  String name;
  int capacity;
  int currentCapacity;
  int minTemperature;
  int maxTemperature;

  public Storage(String name, int capacity, int minTemperature, int maxTemperature) {
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

  public void removeGrocery(Grocery grocery, float amount) {
    float remainingAmount = grocery.getAmount() - amount;
    if (remainingAmount < 0) {
      throw new IllegalArgumentException("Removing more than available");
    }
    currentCapacity -= amount;
  }

  public int getCapacity() {
    return capacity;
  }

  public int getCurrentCapacity() {
    return currentCapacity;
  }

  public String getName() {
    return name;
  }
}
