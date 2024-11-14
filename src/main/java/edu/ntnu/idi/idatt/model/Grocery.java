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
  Double preferredStorageTemperature;

  /**
   * Constructor for the Grocery class. We use a builder to one have one constructer where only the
   * name, amount and measurement unit are required.
   *
   * @param builder The builder object that contains the values for the Grocery object
   */
  public Grocery(Builder builder) {
    this.name = builder.name;
    this.amount = builder.amount;
    this.measurementUnit = builder.measurementUnit;
    this.expirationDate = builder.expirationDate;
    this.price = builder.price;
    this.preferredStorageTemperature = builder.preferredStorageTemperature;
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
    this.price = price;
  }

  /**
   * Get the preferred storage temperature of the grocery item.
   *
   * @return The preferred storage temperature of the grocery item
   */
  public Double getPreferredStorageTemperature() {
    return preferredStorageTemperature;
  }

  /**
   * Set the preferred storage temperature of the grocery item.
   *
   * @param preferredStorageTemperature set the preferred storage temperature of the grocery item
   */
  public void setPreferredStorageTemperature(Double preferredStorageTemperature) {
    this.preferredStorageTemperature = preferredStorageTemperature;
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
        + ", prefferedStorageTemperature="
        + preferredStorageTemperature
        + '}';
  }

  /** Builder class for the Grocery class. */
  public static class Builder {
    protected String name;
    protected Double amount;
    protected MeasurementUnit measurementUnit;
    protected LocalDate expirationDate = null;
    protected Double price = 0.;
    protected Double preferredStorageTemperature = null;

    /**
     * Constructor for the builder class. This constructor only takes the required fields for the
     * Grocery class.
     *
     * @param name The name of the grocery item
     * @param amount The amount of the grocery item
     * @param measurementUnit The measurement unit of the grocery item
     */
    public Builder(String name, Double amount, MeasurementUnit measurementUnit) {
      this.name = name;
      this.amount = amount;
      this.measurementUnit = measurementUnit;
    }

    /**
     * Set the expiration date of the grocery item.
     *
     * @param expirationDate The expiration date of the grocery item
     * @return The builder object
     */
    public Builder expirationDate(LocalDate expirationDate) {
      this.expirationDate = expirationDate;
      return this;
    }

    /**
     * The price of the grocery item.
     *
     * @param price The price of the grocery item
     * @return The builder object
     */
    public Builder price(Double price) {
      this.price = price;
      return this;
    }

    /**
     * The preferred storage temperature of the grocery item.
     *
     * @param preferredStorageTemperature The preferred storage temperature of the grocery item
     * @return The builder object
     */
    public Builder preferredStorageTemperature(Double preferredStorageTemperature) {
      this.preferredStorageTemperature = preferredStorageTemperature;
      return this;
    }

    /**
     * Build the Grocery object.
     *
     * @return The Grocery object
     */
    public Grocery build() {
      return new Grocery(this);
    }
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
