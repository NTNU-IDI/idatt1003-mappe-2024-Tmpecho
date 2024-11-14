package edu.ntnu.idi.idatt.model;

/** A class representing a fridge storage. */
public class Fridge extends Storage {
  double temperature;

  /**
   * Creates an instance of a fridge storage.
   *
   * @param name name of the fridge
   * @param capacity capacity of the fridge
   * @param minTemperature minimum temperature of the fridge
   * @param maxTemperature maximum temperature of the fridge
   */
  public Fridge(String name, double capacity, double minTemperature, double maxTemperature) {
    super(name, capacity, minTemperature, maxTemperature);
  }

  /**
   * Returns the temperature of the fridge.
   *
   * @param temperature temperature of the fridge
   */
  public void setTemperature(double temperature) {
    if (temperature < minTemperature || temperature > maxTemperature) {
      throw new IllegalArgumentException("Temperature out of range");
    }
    this.temperature = temperature;
  }
}
