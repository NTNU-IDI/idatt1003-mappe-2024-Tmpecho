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
  int prefferedStorageTemperature;

  public Grocery(String name, Float amount, MeasurementUnit measurementUnit) {
    this.name = name;
    this.amount = amount;
    this.measurementUnit = measurementUnit;
  }

  public Grocery(
      String name, Float amount, MeasurementUnit measurementUnit, LocalDate expirationDate) {
    this.name = name;
    this.amount = amount;
    this.measurementUnit = measurementUnit;
    this.expirationDate = expirationDate;
  }

  public Grocery(String name, Float amount, MeasurementUnit measurementUnit, Float price) {
    this.name = name;
    this.amount = amount;
    this.measurementUnit = measurementUnit;
    this.price = price;
  }

  public Grocery(
      String name, Float amount, MeasurementUnit measurementUnit, int prefferedStorageTemperature) {
    this.name = name;
    this.amount = amount;
    this.measurementUnit = measurementUnit;
    this.prefferedStorageTemperature = prefferedStorageTemperature;
  }

  public Grocery(
      String name,
      Float amount,
      MeasurementUnit measurementUnit,
      LocalDate expirationDate,
      Float price) {
    this.name = name;
    this.amount = amount;
    this.measurementUnit = measurementUnit;
    this.expirationDate = expirationDate;
    this.price = price;
  }

  public Grocery(
      String name,
      Float amount,
      MeasurementUnit measurementUnit,
      LocalDate expirationDate,
      int prefferedStorageTemperature) {
    this.name = name;
    this.amount = amount;
    this.measurementUnit = measurementUnit;
    this.expirationDate = expirationDate;
    this.prefferedStorageTemperature = prefferedStorageTemperature;
  }

  public Grocery(
      String name,
      Float amount,
      MeasurementUnit measurementUnit,
      LocalDate expirationDate,
      Float price,
      int prefferedStorageTemperature) {
    this.name = name;
    this.amount = amount;
    this.measurementUnit = measurementUnit;
    this.expirationDate = expirationDate;
    this.price = price;
    this.prefferedStorageTemperature = prefferedStorageTemperature;
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

  public int getPrefferedStorageTemperature() {
    return prefferedStorageTemperature;
  }

  public void setPrefferedStorageTemperature(int prefferedStorageTemperature) {
    this.prefferedStorageTemperature = prefferedStorageTemperature;
  }
}
