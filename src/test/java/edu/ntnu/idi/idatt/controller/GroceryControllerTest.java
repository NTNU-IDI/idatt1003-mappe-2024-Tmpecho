package edu.ntnu.idi.idatt.controller;

import static org.junit.jupiter.api.Assertions.*;

import edu.ntnu.idi.idatt.model.Grocery;
import edu.ntnu.idi.idatt.model.MeasurementUnit;
import java.time.LocalDate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/** Tests the GroceryController for creating and calculating grocery values. */
class GroceryControllerTest {
  private GroceryController groceryController;

  @BeforeEach
  void setUp() {
    groceryController = new GroceryController();
  }

  /** Tests creating a grocery with valid input. */
  @Test
  void createGroceryTest() {
    Grocery grocery =
        groceryController.createGrocery("Milk", 2.0, "liter", LocalDate.now().plusDays(7), 1.5);

    assertEquals("Milk", grocery.getName());
    assertEquals(2.0, grocery.getAmount());
    assertEquals(MeasurementUnit.LITER, grocery.getMeasurementUnit());
    assertEquals(LocalDate.now().plusDays(7), grocery.getExpirationDate());
    assertEquals(1.5, grocery.getPrice());
  }

  /** Tests creating a grocery with an invalid measurement unit. */
  @Test
  void createGroceryWithInvalidUnitTest() {
    assertThrows(
        IllegalArgumentException.class,
        () -> groceryController.createGrocery("Bread", 1.0, "unknownUnit", null, 2.0));
  }

  /** Tests creating a grocery with a negative amount. */
  @Test
  void createGroceryWithNegativeAmountTest() {
    assertThrows(
        IllegalArgumentException.class,
        () -> groceryController.createGrocery("Cheese", -1.0, "liter", null, 2.0));
  }

  /** Tests creating a grocery with zero price. */
  @Test
  void createGroceryWithZeroPriceTest() {
    Grocery grocery = groceryController.createGrocery("Water", 1.0, "liter", null, 0.0);

    assertEquals(0.0, grocery.getPrice());
  }

  /** Tests creating a grocery with a negative price. */
  @Test
  void createGroceryWithNegativePriceTest() {
    assertThrows(
        IllegalArgumentException.class,
        () -> groceryController.createGrocery("Juice", 1.0, "liter", null, -2.0));
  }

  /** Tests calculating the value of a grocery. */
  @Test
  void calculateGroceryValueTest() {
    Grocery grocery =
        new Grocery("Milk", 2.0, MeasurementUnit.LITER, LocalDate.of(2024, 12, 1), 1.5);

    double value = GroceryController.calculateGroceryValue(grocery);

    assertEquals(3.0, value);
  }

  /** Tests calculating the value of a grocery with no price. */
  @Test
  void calculateGroceryValueNullPriceTest() {
    Grocery grocery = new Grocery("Milk", 2.0, MeasurementUnit.LITER, null, null);

    double value = GroceryController.calculateGroceryValue(grocery);

    assertEquals(0.0, value);
  }
}
