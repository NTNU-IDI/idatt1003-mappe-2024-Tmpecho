package edu.ntnu.idi.idatt.controller;

import static org.junit.jupiter.api.Assertions.*;

import edu.ntnu.idi.idatt.model.Grocery;
import edu.ntnu.idi.idatt.model.MeasurementUnit;
import java.time.LocalDate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/** Test class for the GroceryController class. */
class GroceryControllerTest {

  private GroceryController groceryController;

  /** Sets up a new GroceryController object before each test. */
  @BeforeEach
  void setUp() {
    groceryController = new GroceryController();
  }

  /** Tests creating a grocery. */
  @Test
  void createGroceryTest() {
    Grocery grocery =
        groceryController.createGrocery("Milk", 2.0, "liter", LocalDate.now().plusDays(7), 1.5);
    assertEquals("Milk", grocery.getName());
    assertEquals(2.0, grocery.getAmount());
    assertEquals(MeasurementUnit.LITER, grocery.getMeasurementUnit());
    assertEquals(LocalDate.now().plusDays(7), grocery.getExpirationDate());
    assertEquals(1.5, grocery.getPrice());

    // Test with invalid unit
    assertThrows(
        IllegalArgumentException.class,
        () -> groceryController.createGrocery("Bread", 1.0, "unknownUnit", null, 2.0));
  }

  /** Tests calculating grocery value. */
  @Test
  void calculateGroceryValueTest() {
    Grocery grocery =
        new Grocery("Milk", 2.0, MeasurementUnit.LITER, LocalDate.of(2024, 12, 1), 1.5);
    double value = groceryController.calculateGroceryValue(grocery);
    assertEquals(3.0, value);
  }
}
