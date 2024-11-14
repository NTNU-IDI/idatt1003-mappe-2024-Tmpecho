package edu.ntnu.idi.idatt.model;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

/** Test class for the Grocery class. */
class GroceryTest {
  private Grocery milk;
  private Grocery bread;
  private Grocery soysauce;

  /** Sets up new grocery objects before each test. */
  @org.junit.jupiter.api.BeforeEach
  void setUp() {
    milk =
        new Grocery.Builder("Milk", 1., MeasurementUnit.valueOf("LITER"))
            .expirationDate(LocalDate.of(2024, 10, 1))
            .price(1.5)
            .preferredStorageTemperature(4.0)
            .build();

    bread =
        new Grocery.Builder("Bread", 1., MeasurementUnit.valueOf("PCS"))
            .expirationDate(LocalDate.of(2024, 10, 1))
            .price(1.5)
            .preferredStorageTemperature(4.0)
            .build();

    soysauce = new Grocery.Builder("Soysauce", 1., MeasurementUnit.valueOf("LITER")).build();
  }

  /** Tests the getName method. */
  @org.junit.jupiter.api.Test
  void getName() {
    assertEquals("Milk", milk.getName());
    assertEquals("Bread", bread.getName());

    assertNotEquals("Bread", milk.getName());
    assertNotEquals("Milk", bread.getName());
  }

  /** Tests the setName method. */
  @org.junit.jupiter.api.Test
  void setName() {
    milk.setName("Whole Milk");
    bread.setName("Whole Bread");

    assertEquals("Whole Milk", milk.getName());
    assertEquals("Whole Bread", bread.getName());

    assertNotEquals("Milk", milk.getName());
    assertNotEquals("Break", bread.getName());
  }

  /** Tests the getAmount method. */
  @org.junit.jupiter.api.Test
  void getAmount() {
    assertEquals(1.0, milk.getAmount());
    assertEquals(1.0, bread.getAmount());

    assertNotEquals(2.0, milk.getAmount());
    assertNotEquals(2.0, bread.getAmount());
  }

  /** Tests the setAmount method. */
  @org.junit.jupiter.api.Test
  void setAmount() {
    milk.setAmount(2.0);
    bread.setAmount(2.0);

    assertEquals(2, milk.getAmount());
    assertEquals(2, bread.getAmount());

    assertNotEquals(1, milk.getAmount());
    assertNotEquals(1, bread.getAmount());
  }

  /** Tests the getMeasurementUnit method. */
  @org.junit.jupiter.api.Test
  void getMeasurementUnit() {
    assertEquals(MeasurementUnit.valueOf("LITER"), milk.getMeasurementUnit());
    assertEquals(MeasurementUnit.valueOf("PCS"), bread.getMeasurementUnit());

    assertNotEquals(MeasurementUnit.valueOf("PCS"), milk.getMeasurementUnit());
    assertNotEquals(MeasurementUnit.valueOf("LITER"), bread.getMeasurementUnit());
  }

  /** Tests the setMeasurementUnit method. */
  @org.junit.jupiter.api.Test
  void setMeasurementUnit() {
    milk.setMeasurementUnit(MeasurementUnit.valueOf("PCS"));
    bread.setMeasurementUnit(MeasurementUnit.valueOf("LITER"));

    assertEquals(MeasurementUnit.valueOf("PCS"), milk.getMeasurementUnit());
    assertEquals(MeasurementUnit.valueOf("LITER"), bread.getMeasurementUnit());

    assertNotEquals(MeasurementUnit.valueOf("LITER"), milk.getMeasurementUnit());
    assertNotEquals(MeasurementUnit.valueOf("PCS"), bread.getMeasurementUnit());
  }

  /** Tests the getExpirationDate method. */
  @org.junit.jupiter.api.Test
  void getExpirationDate() {
    assertEquals(LocalDate.of(2024, 10, 1), milk.getExpirationDate());
    assertEquals(LocalDate.of(2024, 10, 1), bread.getExpirationDate());
    assertNull(soysauce.getExpirationDate());

    assertNotEquals(LocalDate.of(2024, 10, 2), milk.getExpirationDate());
    assertNotEquals(LocalDate.of(2024, 10, 2), bread.getExpirationDate());
    assertNotEquals(LocalDate.of(2024, 10, 1), soysauce.getExpirationDate());
  }

  /** Tests the setExpirationDate method. */
  @org.junit.jupiter.api.Test
  void setExpirationDate() {
    milk.setExpirationDate(LocalDate.of(2024, 10, 2));
    bread.setExpirationDate(LocalDate.of(2024, 10, 2));

    assertEquals(LocalDate.of(2024, 10, 2), milk.getExpirationDate());
    assertEquals(LocalDate.of(2024, 10, 2), bread.getExpirationDate());

    assertNotEquals(LocalDate.of(2024, 10, 1), milk.getExpirationDate());
    assertNotEquals(LocalDate.of(2024, 10, 1), bread.getExpirationDate());
  }

  /** Tests the getPrice method. */
  @org.junit.jupiter.api.Test
  void getPrice() {
    assertEquals(1.5, milk.getPrice());
    assertEquals(1.5, bread.getPrice());
    assertEquals(0.0, soysauce.getPrice());

    assertNotEquals(2.5, milk.getPrice());
    assertNotEquals(2.5, bread.getPrice());
    assertNotEquals(null, soysauce.getPrice());
  }

  /** Tests the setPrice method. */
  @org.junit.jupiter.api.Test
  void setPrice() {
    milk.setPrice(2.5);
    bread.setPrice(2.5);

    assertEquals(2.5, milk.getPrice());
    assertEquals(2.5, bread.getPrice());

    assertNotEquals(1.5, milk.getPrice());
    assertNotEquals(1.5, bread.getPrice());
  }

  /** Tests the getPrefferedStorageTemperature method. */
  @org.junit.jupiter.api.Test
  void getPrefferedStorageTemperature() {
    assertEquals(4.0, milk.getPreferredStorageTemperature());
    assertEquals(4.0, bread.getPreferredStorageTemperature());
    assertNull(soysauce.getPreferredStorageTemperature());

    assertNotEquals(5.0, milk.getPreferredStorageTemperature());
    assertNotEquals(5.0, bread.getPreferredStorageTemperature());
    assertNotEquals(0.0, soysauce.getPreferredStorageTemperature());
  }

  /** Tests the setPrefferedStorageTemperature method. */
  @org.junit.jupiter.api.Test
  void setPrefferedStorageTemperature() {
    milk.setPreferredStorageTemperature(5.0);
    bread.setPreferredStorageTemperature(5.0);

    assertEquals(5.0, milk.getPreferredStorageTemperature());
    assertEquals(5.0, bread.getPreferredStorageTemperature());

    assertNotEquals(4.0, milk.getPreferredStorageTemperature());
    assertNotEquals(4.0, bread.getPreferredStorageTemperature());
  }
}
