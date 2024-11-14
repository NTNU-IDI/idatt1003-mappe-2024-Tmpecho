package edu.ntnu.idi.idatt.model;

/** A class representing a cupboard storage. */
public class Cupboard extends Storage {
  /**
   * Creates an instance of a cupboard storage.
   *
   * @param name name of the cupboard
   * @param capacity capacity of the cupboard
   * @param minTemperature minimum temperature of the cupboard
   * @param maxTemperature maximum temperature of the cupboard
   */
  public Cupboard(String name, double capacity, double minTemperature, double maxTemperature) {
    super(name, capacity, minTemperature, maxTemperature);
  }
}
