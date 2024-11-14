package edu.ntnu.idi.idatt.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/** Test class for the Fridge class. */
class FridgeTest {
  Fridge fridge;

  /** Sets up a new fridge object before each test. */
  @org.junit.jupiter.api.BeforeEach
  void setUp() {
    fridge = new Fridge("Fridge", 100, -10, 10);
  }

  /** Tests the setTemperature method. */
  @Test
  void setTemperature() {
    fridge.setTemperature(0);
    assertEquals(0, fridge.temperature);

    assertThrows(IllegalArgumentException.class, () -> fridge.setTemperature(-11));
    assertThrows(IllegalArgumentException.class, () -> fridge.setTemperature(11));
  }
}
