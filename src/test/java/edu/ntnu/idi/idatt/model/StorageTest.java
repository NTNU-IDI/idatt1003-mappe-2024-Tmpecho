package edu.ntnu.idi.idatt.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

class StorageTest {
  Storage storage;

  @BeforeEach
  void setUp() {
    storage = new Storage("Storage", 100, -10, 10);
  }

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
