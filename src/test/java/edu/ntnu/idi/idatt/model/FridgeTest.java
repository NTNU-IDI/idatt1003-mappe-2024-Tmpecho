package edu.ntnu.idi.idatt.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/** Tests the Fridge class for adding and removing groceries. */
class FridgeTest {
  private Fridge fridge;

  @BeforeEach
  void setUp() {
    fridge = new Fridge("Fridge", 100);
  }

  /** Tests the fridge constructor and initial state. */
  @Test
  void constructorTest() {
    assertEquals("Fridge", fridge.getName());
    assertEquals(100.0, fridge.getCapacity());
    assertEquals(0.0, fridge.getCurrentCapacity());
    assertEquals(0.0, fridge.minTemperature);
    assertEquals(0.0, fridge.maxTemperature);
  }

  /** Tests adding and removing groceries from the fridge. */
  @Test
  void addAndRemoveGroceryTest() {
    Grocery juice = new Grocery("Juice", 5.0, MeasurementUnit.LITER, null, 2.5);
    fridge.addGrocery(juice);

    assertEquals(5.0, fridge.getCurrentCapacity());

    fridge.removeGrocery(juice, 2.0);

    assertEquals(3.0, fridge.getCurrentCapacity());
  }

  /** Tests removing a grocery amount exceeding what's available. */
  @Test
  void removeGroceryExceedingAmountTest() {
    Grocery juice = new Grocery("Juice", 5.0, MeasurementUnit.LITER, null, 2.5);
    fridge.addGrocery(juice);

    assertThrows(IllegalArgumentException.class, () -> fridge.removeGrocery(juice, 10.0));
  }

  /** Tests removing a negative amount. */
  @Test
  void removeGroceryNegativeAmountTest() {
    Grocery juice = new Grocery("Juice", 5.0, MeasurementUnit.LITER, null, 2.5);
    fridge.addGrocery(juice);

    assertThrows(IllegalArgumentException.class, () -> fridge.removeGrocery(juice, -1.0));
  }
}
