package edu.ntnu.idi.idatt.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/** Test class for the Fridge class. */
class FridgeTest {
  private Fridge fridge;

  /** Sets up a new fridge object before each test. */
  @BeforeEach
  void setUp() {
    fridge = new Fridge("Fridge", 100);
  }

  /** Tests the constructor and inherited methods. */
  @Test
  void constructorTest() {
    assertEquals("Fridge", fridge.getName());
    assertEquals(100.0, fridge.getCapacity());
    assertEquals(0.0, fridge.getCurrentCapacity());
    assertEquals(0.0, fridge.minTemperature);
    assertEquals(0.0, fridge.maxTemperature);
  }

  /** Tests adding and removing groceries. */
  @Test
  void addAndRemoveGroceryTest() {
    Grocery juice = new Grocery("Juice", 5.0, MeasurementUnit.LITER, null, 2.5);
    fridge.addGrocery(juice);
    assertEquals(5.0, fridge.getCurrentCapacity());

    fridge.removeGrocery(juice, 2.0);
    assertEquals(3.0, fridge.getCurrentCapacity());
  }
}
