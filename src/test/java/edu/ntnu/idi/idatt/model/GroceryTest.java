package edu.ntnu.idi.idatt.model;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

class GroceryTest {
  private Grocery milk;
  private Grocery bread;
  private Grocery soysauce;

  @org.junit.jupiter.api.BeforeEach
  void setUp() {
    milk =
        new Grocery.Builder("Milk", 1., MeasurementUnit.valueOf("LITER"))
            .expirationDate(LocalDate.of(2024, 10, 1))
            .price(1.5)
            .prefferedStorageTemperature(4.0)
            .build();

    bread =
        new Grocery.Builder("Bread", 1., MeasurementUnit.valueOf("PCS"))
            .expirationDate(LocalDate.of(2024, 10, 1))
            .price(1.5)
            .prefferedStorageTemperature(4.0)
            .build();

    soysauce = new Grocery.Builder("Soysauce", 1., MeasurementUnit.valueOf("LITER")).build();
  }

  @org.junit.jupiter.api.Test
  void getName() {
    assertEquals("Milk", milk.getName());
    assertEquals("Bread", bread.getName());

    assertNotEquals("Bread", milk.getName());
    assertNotEquals("Milk", bread.getName());
  }

  @org.junit.jupiter.api.Test
  void setName() {
    milk.setName("Whole Milk");
    bread.setName("Whole Bread");

    assertEquals("Whole Milk", milk.getName());
    assertEquals("Whole Bread", bread.getName());

    assertNotEquals("Milk", milk.getName());
    assertNotEquals("Break", bread.getName());
  }

  @org.junit.jupiter.api.Test
  void getAmount() {
    assertEquals(1.0, milk.getAmount());
    assertEquals(1.0, bread.getAmount());

    assertNotEquals(2.0, milk.getAmount());
    assertNotEquals(2.0, bread.getAmount());
  }

  @org.junit.jupiter.api.Test
  void setAmount() {
    milk.setAmount(2.0);
    bread.setAmount(2.0);

    assertEquals(2, milk.getAmount());
    assertEquals(2, bread.getAmount());

    assertNotEquals(1, milk.getAmount());
    assertNotEquals(1, bread.getAmount());
  }

  @org.junit.jupiter.api.Test
  void getMeasurementUnit() {
    assertEquals(MeasurementUnit.valueOf("LITER"), milk.getMeasurementUnit());
    assertEquals(MeasurementUnit.valueOf("PCS"), bread.getMeasurementUnit());

    assertNotEquals(MeasurementUnit.valueOf("PCS"), milk.getMeasurementUnit());
    assertNotEquals(MeasurementUnit.valueOf("LITER"), bread.getMeasurementUnit());
  }

  @org.junit.jupiter.api.Test
  void setMeasurementUnit() {
    milk.setMeasurementUnit(MeasurementUnit.valueOf("PCS"));
    bread.setMeasurementUnit(MeasurementUnit.valueOf("LITER"));

    assertEquals(MeasurementUnit.valueOf("PCS"), milk.getMeasurementUnit());
    assertEquals(MeasurementUnit.valueOf("LITER"), bread.getMeasurementUnit());

    assertNotEquals(MeasurementUnit.valueOf("LITER"), milk.getMeasurementUnit());
    assertNotEquals(MeasurementUnit.valueOf("PCS"), bread.getMeasurementUnit());
  }

  @org.junit.jupiter.api.Test
  void getExpirationDate() {
    assertEquals(LocalDate.of(2024, 10, 1), milk.getExpirationDate());
    assertEquals(LocalDate.of(2024, 10, 1), bread.getExpirationDate());
    assertNull(soysauce.getExpirationDate());

    assertNotEquals(LocalDate.of(2024, 10, 2), milk.getExpirationDate());
    assertNotEquals(LocalDate.of(2024, 10, 2), bread.getExpirationDate());
    assertNotEquals(LocalDate.of(2024, 10, 1), soysauce.getExpirationDate());
  }

  @org.junit.jupiter.api.Test
  void setExpirationDate() {
    milk.setExpirationDate(LocalDate.of(2024, 10, 2));
    bread.setExpirationDate(LocalDate.of(2024, 10, 2));

    assertEquals(LocalDate.of(2024, 10, 2), milk.getExpirationDate());
    assertEquals(LocalDate.of(2024, 10, 2), bread.getExpirationDate());

    assertNotEquals(LocalDate.of(2024, 10, 1), milk.getExpirationDate());
    assertNotEquals(LocalDate.of(2024, 10, 1), bread.getExpirationDate());
  }

  @org.junit.jupiter.api.Test
  void getPrice() {
    assertEquals(1.5, milk.getPrice());
    assertEquals(1.5, bread.getPrice());
    assertEquals(0.0, soysauce.getPrice());

    assertNotEquals(2.5, milk.getPrice());
    assertNotEquals(2.5, bread.getPrice());
    assertNotEquals(null, soysauce.getPrice());
  }

  @org.junit.jupiter.api.Test
  void setPrice() {
    milk.setPrice(2.5);
    bread.setPrice(2.5);

    assertEquals(2.5, milk.getPrice());
    assertEquals(2.5, bread.getPrice());

    assertNotEquals(1.5, milk.getPrice());
    assertNotEquals(1.5, bread.getPrice());
  }

  @org.junit.jupiter.api.Test
  void getPrefferedStorageTemperature() {
    assertEquals(4.0, milk.getPrefferedStorageTemperature());
    assertEquals(4.0, bread.getPrefferedStorageTemperature());
    assertNull(soysauce.getPrefferedStorageTemperature());

    assertNotEquals(5.0, milk.getPrefferedStorageTemperature());
    assertNotEquals(5.0, bread.getPrefferedStorageTemperature());
    assertNotEquals(0.0, soysauce.getPrefferedStorageTemperature());
  }

  @org.junit.jupiter.api.Test
  void setPrefferedStorageTemperature() {
    milk.setPrefferedStorageTemperature(5.0);
    bread.setPrefferedStorageTemperature(5.0);

    assertEquals(5.0, milk.getPrefferedStorageTemperature());
    assertEquals(5.0, bread.getPrefferedStorageTemperature());

    assertNotEquals(4.0, milk.getPrefferedStorageTemperature());
    assertNotEquals(4.0, bread.getPrefferedStorageTemperature());
  }
}
