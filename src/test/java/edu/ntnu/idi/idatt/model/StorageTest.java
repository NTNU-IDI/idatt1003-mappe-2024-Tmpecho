package edu.ntnu.idi.idatt.model;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/** Test class for the Storage class. */
class StorageTest {
  private Storage storage;
  private Grocery milk;
  private Grocery bread;

  /** Sets up a new storage object before each test. */
  @BeforeEach
  void setUp() {
    storage = new Storage("Pantry", 100, -10, 30);
    milk = new Grocery("Milk", 10.0, MeasurementUnit.LITER, LocalDate.of(2024, 10, 1), 1.5);
    bread = new Grocery("Bread", 50.0, MeasurementUnit.PCS, LocalDate.of(2024, 9, 15), 2.0);
  }

  /** Tests the constructor and getters. */
  @Test
  void constructorTest() {
    assertEquals("Pantry", storage.getName());
    assertEquals(100.0, storage.getCapacity());
    assertEquals(0.0, storage.getCurrentCapacity());
    assertEquals(-10.0, storage.minTemperature);
    assertEquals(30.0, storage.maxTemperature);
  }

  /** Tests adding groceries within capacity limits. */
  @Test
  void addGroceryWithinCapacity() {
    storage.addGrocery(milk);
    assertEquals(10.0, storage.getCurrentCapacity());

    storage.addGrocery(bread);
    assertEquals(60.0, storage.getCurrentCapacity());
  }

  /** Tests adding a grocery that exceeds capacity. */
  @Test
  void addGroceryExceedsCapacity() {
    Grocery largeGrocery = new Grocery("Rice", 150.0, MeasurementUnit.KILOGRAM, null, 1.0);
    Exception exception =
        assertThrows(IllegalArgumentException.class, () -> storage.addGrocery(largeGrocery));
    assertEquals("Not enough capacity in storage", exception.getMessage());
    assertEquals(0.0, storage.getCurrentCapacity());
  }

  /** Tests removing groceries within current capacity. */
  @Test
  void removeGroceryWithinCapacity() {
    storage.addGrocery(milk);
    storage.removeGrocery(milk, 5.0);
    assertEquals(5.0, storage.getCurrentCapacity());

    storage.removeGrocery(milk, 5.0);
    assertEquals(0.0, storage.getCurrentCapacity());
  }

  /** Tests removing more than the grocery amount. */
  @Test
  void removeGroceryExceedsAmount() {
    storage.addGrocery(bread);
    Exception exception =
        assertThrows(IllegalArgumentException.class, () -> storage.removeGrocery(bread, 60.0));
    assertEquals("Removing more than available", exception.getMessage());
    assertEquals(50.0, storage.getCurrentCapacity());
  }

  /** Tests getCapacity method. */
  @Test
  void getCapacity() {
    assertEquals(100.0, storage.getCapacity());
  }

  /** Tests getCurrentCapacity method after adding and removing groceries. */
  @Test
  void getCurrentCapacity() {
    assertEquals(0.0, storage.getCurrentCapacity());
    storage.addGrocery(milk);
    assertEquals(10.0, storage.getCurrentCapacity());
    storage.addGrocery(bread);
    assertEquals(60.0, storage.getCurrentCapacity());
    storage.removeGrocery(bread, 10.0);
    assertEquals(50.0, storage.getCurrentCapacity());
  }

  /** Tests getName method. */
  @Test
  void getName() {
    assertEquals("Pantry", storage.getName());
  }

  /** Tests adding multiple groceries exceeding capacity over multiple additions. */
  @Test
  void multipleAdditionsExceedingCapacity() {
    storage.addGrocery(milk);
    storage.addGrocery(bread);
    Grocery eggs = new Grocery("Eggs", 45.0, MeasurementUnit.PCS, null, 0.2);

    Exception exception =
        assertThrows(IllegalArgumentException.class, () -> storage.addGrocery(eggs));
    assertEquals("Not enough capacity in storage", exception.getMessage());
    assertEquals(60.0, storage.getCurrentCapacity());
  }

  /** Tests removing negative amount (should throw exception). */
  @Test
  void removeNegativeAmount() {
    storage.addGrocery(milk);
    Exception exception =
        assertThrows(IllegalArgumentException.class, () -> storage.removeGrocery(milk, -5.0));
    assertEquals("Amount to remove cannot be negative or zero", exception.getMessage());
  }

  /** Tests removing a grocery from storage */
  @Test
  void removeGrocery() {
    storage.addGrocery(milk);
    storage.removeGrocery(milk);
    assertEquals(0.0, storage.getCurrentCapacity());
  }
}
