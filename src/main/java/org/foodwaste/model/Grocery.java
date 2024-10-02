package org.foodwaste.model;

import java.time.LocalDate;

/**
 * Represents a grocery item.
 *
 * <p>Each grocery item has a name, amount, measurement unit, expiration date, price, and preferred
 * storage temperature. Only the name amount, and measurmentUnit are required.
 */
public class Grocery {
  String name;
  Float amount;
  MeasurementUnit measurementUnit;
  LocalDate expirationDate;
  Float price; // Price per unit in non-specified currency
  Float prefferedStorageTemperature;

  /**
   * Constructor for the Grocery class. We use a builder to one have one constructer where only the
   * name, amount and measurement unit are required.
   *
   * @param builder The builder object that contains the values for the Grocery object.
   */
  public Grocery(Builder builder) {
    this.name = builder.name;
    this.amount = builder.amount;
    this.measurementUnit = builder.measurementUnit;
    this.expirationDate = builder.expirationDate;
    this.price = builder.price;
    this.prefferedStorageTemperature = builder.prefferedStorageTemperature;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Float getAmount() {
    return amount;
  }

  public void setAmount(Float amount) {
    this.amount = amount;
  }

  public MeasurementUnit getMeasurementUnit() {
    return measurementUnit;
  }

  public void setMeasurementUnit(MeasurementUnit measurementUnit) {
    this.measurementUnit = measurementUnit;
  }

  public LocalDate getExpirationDate() {
    return expirationDate;
  }

  public void setExpirationDate(LocalDate expirationDate) {
    this.expirationDate = expirationDate;
  }

  public Float getPrice() {
    return price;
  }

  public void setPrice(Float price) {
    this.price = price;
  }

  public Float getPrefferedStorageTemperature() {
    return prefferedStorageTemperature;
  }

  public void setPrefferedStorageTemperature(Float prefferedStorageTemperature) {
    this.prefferedStorageTemperature = prefferedStorageTemperature;
  }

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
        + prefferedStorageTemperature
        + '}';
  }

  /** Builder class for the Grocery class. */
  public static class Builder {
    protected String name;
    protected Float amount;
    protected MeasurementUnit measurementUnit;
    protected LocalDate expirationDate = null;
    protected Float price = 0f;
    protected Float prefferedStorageTemperature = null;

    /**
     * Constructor for the builder class. This constructor only takes the required fields for the
     * Grocery class.
     *
     * @param name The name of the grocery item.
     * @param amount The amount of the grocery item.
     * @param measurementUnit The measurement unit of the grocery item.
     */
    public Builder(String name, Float amount, MeasurementUnit measurementUnit) {
      this.name = name;
      this.amount = amount;
      this.measurementUnit = measurementUnit;
    }

    public Builder expirationDate(LocalDate expirationDate) {
      this.expirationDate = expirationDate;
      return this;
    }

    public Builder price(Float price) {
      this.price = price;
      return this;
    }

    public Builder prefferedStorageTemperature(Float prefferedStorageTemperature) {
      this.prefferedStorageTemperature = prefferedStorageTemperature;
      return this;
    }

    public Grocery build() {
      return new Grocery(this);
    }
  }
}
