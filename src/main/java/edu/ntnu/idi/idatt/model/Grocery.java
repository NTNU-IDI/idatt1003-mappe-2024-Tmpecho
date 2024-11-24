package edu.ntnu.idi.idatt.model;

import java.time.LocalDate;

/**
 * Represents a grocery item.
 *
 * <p>Each grocery item has a name, amount, measurement unit, expiration date, price, and preferred
 * storage temperature. Only the name amount, and measurmentUnit are required.
 */
public class Grocery implements Comparable<Grocery> {
  String name;
  Double amount;
  MeasurementUnit measurementUnit;
  LocalDate expirationDate;
  Double price; // Price per unit in non-specified currency

  /**
   * Constructor for the Grocery class. We use a builder to one have one constructer where only the
   * name, amount and measurement unit are required.
   */
  public Grocery(
      String name,
      Double amount,
      MeasurementUnit measurementUnit,
      LocalDate expirationDate,
      Double price) {
    if (name == null) {
      throw new IllegalArgumentException("Name cannot be null");
    }
    if (amount < 0) {
      throw new IllegalArgumentException("Amount cannot be negative");
    }
    if (price < 0) {
      throw new IllegalArgumentException("Price cannot be negative");
    }
    this.name = name;
    this.amount = amount;
    this.measurementUnit = measurementUnit;
    this.expirationDate = expirationDate;
    this.price = price;
  }

  /**
   * Get the name of the grocery item.
   *
   * @return The name of the grocery item
   */
  public String getName() {
    return name;
  }

  /**
   * Set the name of the grocery item.
   *
   * @param name The name of the grocery item
   */
  public void setName(String name) {
    if (name == null) {
      throw new IllegalArgumentException("Name cannot be null");
    }
    this.name = name;
  }

  /**
   * Get the amount of the grocery item.
   *
   * @return The amount of the grocery item
   */
  public Double getAmount() {
    return amount;
  }

  /**
   * Set the amount of the grocery item.
   *
   * @param amount The amount of the grocery item
   */
  public void setAmount(Double amount) {
    if (amount < 0) {
      throw new IllegalArgumentException("Amount cannot be negative");
    }
    this.amount = amount;
  }

  /**
   * Get the measurement unit of the grocery item.
   *
   * @return The measurement unit of the grocery item
   */
  public MeasurementUnit getMeasurementUnit() {
    return measurementUnit;
  }

  /**
   * Set the measurement unit of the grocery item.
   *
   * @param measurementUnit The measurement unit of the grocery item
   */
  public void setMeasurementUnit(MeasurementUnit measurementUnit) {
    this.measurementUnit = measurementUnit;
  }

  /**
   * Get the expiration date of the grocery item.
   *
   * @return The expiration date of the grocery item
   */
  public LocalDate getExpirationDate() {
    return expirationDate;
  }

  /**
   * Set the expiration date of the grocery item.
   *
   * @param expirationDate The expiration date of the grocery item
   */
  public void setExpirationDate(LocalDate expirationDate) {
    this.expirationDate = expirationDate;
  }

  /**
   * Get the price of the grocery item.
   *
   * @return The price of the grocery item
   */
  public Double getPrice() {
    return price;
  }

  /**
   * Set the price of the grocery item.
   *
   * @param price The price of the grocery item
   */
  public void setPrice(Double price) {
    if (price < 0) {
      throw new IllegalArgumentException("Price cannot be negative");
    }
    this.price = price;
  }

  /**
   * Returns a string representation of the grocery item.
   *
   * @return A string representation of the grocery item
   */
  @Override
  public String toString() {
    return "Grocery{"
        + "name='"
        + name
        + '\''
        + ", amount="
        + amount
        + ", measurementUnit="
        + measurementUnit
        + ", expirationDate="
        + expirationDate
        + ", price="
        + price
        + '}';
  }

  /**
   * Compares this grocery object with another grocery object based on the name of the grocery item.
   *
   * @param grocery the object to be compared
   * @return a negative integer, zero, or a positive integer as this object is less than, equal to,
   *     or greater than the specified object
   */
  @Override
  public int compareTo(Grocery grocery) {
    return this.name.compareTo(grocery.getName());
  }
}
