package edu.ntnu.idi.idatt.model;

public class Fridge extends Storage {
  double temperature;

  public Fridge(String name, double capacity, double minTemperature, double maxTemperature) {
    super(name, capacity, minTemperature, maxTemperature);
  }

  public void setTemperature(double temperature) {
    if (temperature < minTemperature || temperature > maxTemperature) {
      throw new IllegalArgumentException("Temperature out of range");
    }
    this.temperature = temperature;
  }
}
