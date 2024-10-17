package edu.ntnu.idi.idatt.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class FridgeTest {
  Fridge fridge;

  @org.junit.jupiter.api.BeforeEach
  void setUp() {
    fridge = new Fridge("Fridge", 100, -10, 10);
  }

  @Test
  void setTemperature() {
    fridge.setTemperature(0);
    assertEquals(0, fridge.temperature);

    assertThrows(IllegalArgumentException.class, () -> fridge.setTemperature(-11));
    assertThrows(IllegalArgumentException.class, () -> fridge.setTemperature(11));
  }
}
