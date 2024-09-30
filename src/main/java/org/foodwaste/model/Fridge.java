package org.foodwaste.model;

public class Fridge extends Storage {
  float temperature;

  public Fridge(String name, int capacity, int minTemperature, int maxTemperature) {
    super(name, capacity, minTemperature, maxTemperature);
  }

  public Fridge(String name, int capacity, int minTemperature, int maxTemperature, float temperature) {
    super(name, capacity, minTemperature, maxTemperature);
    this.temperature = temperature;
  }

  public void setTemperature(float temperature) {
    if (temperature < minTemperature || temperature > maxTemperature) {
      throw new IllegalArgumentException("Temperature out of range");
    }
    this.temperature = temperature;
  }
}
