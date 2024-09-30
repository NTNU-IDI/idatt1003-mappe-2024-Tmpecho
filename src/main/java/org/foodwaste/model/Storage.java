package org.foodwaste.model;

public class Storage {
  String name;
  int capacity;
  int minTemperature;
  int maxTemperature;

  public Storage(String name, int capacity, int minTemperature, int maxTemperature) {
    this.name = name;
    this.capacity = capacity;
    this.minTemperature = minTemperature;
    this.maxTemperature = maxTemperature;
  }

  void addGrocery(Grocery grocery, float amount) {}

  void removeGrocery(Grocery grocery, float amount) {}

  void listGroceries() {}

  void listExpiredGroceries() {}
}
