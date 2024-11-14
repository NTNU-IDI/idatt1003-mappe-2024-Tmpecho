package edu.ntnu.idi.idatt.model;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/** Test class for the Storage class. */
class StorageTest {
  Storage storage;

  /** Sets up a new storage object before each test. */
  @BeforeEach
  void setUp() {
    storage = new Storage("Storage", 100, -10, 10);
  }

  /** Tests the addGrocery method. */
  @Test
  void addGrocery() {
    Grocery milk =
        new Grocery.Builder("Milk", 1., MeasurementUnit.valueOf("LITER"))
            .expirationDate(LocalDate.of(2024, 10, 1))
            .price(1.5)
            .preferredStorageTemperature(4.0)
            .build();

    storage.addGrocery(milk);
    assertEquals(1, storage.getCurrentCapacity());
  }

  @Test
  void removeGrocery() {}

  @Test
  void getCapacity() {}

  @Test
  void getCurrentCapacity() {}

  @Test
  void getName() {}
}
