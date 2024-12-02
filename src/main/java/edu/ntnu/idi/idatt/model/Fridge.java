package edu.ntnu.idi.idatt.model;

/** A class representing a fridge storage. */
public class Fridge extends Storage {
  /**
   * Creates an instance of a fridge storage.
   *
   * @param name The name of the fridge
   * @param capacity The capacity of the fridge
   */
  public Fridge(String name, double capacity) {
    super(name, capacity, 0, 0); // Ignore temperature
  }
}
