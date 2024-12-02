package edu.ntnu.idi.idatt.model;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/** Test class for the Grocery class. */
class GroceryTest {
  private Grocery milk;
  private Grocery bread;
  private Grocery soySauce;
  private Grocery anotherMilk;

  /** Sets up new grocery objects before each test. */
  @BeforeEach
  void setUp() {
    milk = new Grocery("Milk", 1.0, MeasurementUnit.LITER, LocalDate.of(2024, 10, 1), 1.5);
    bread = new Grocery("Bread", 2.0, MeasurementUnit.PCS, LocalDate.of(2024, 9, 15), 2.0);
    soySauce = new Grocery("Soy Sauce", 0.5, MeasurementUnit.LITER, null, 3.0);
    anotherMilk = new Grocery("Milk", 1.0, MeasurementUnit.LITER, LocalDate.of(2024, 11, 1), 1.5);
  }

  /** Tests the getName method. */
  @Test
  void getName() {
    assertEquals("Milk", milk.getName());
    assertEquals("Bread", bread.getName());
  }

  /** Tests the setName method with valid and invalid inputs. */
  @Test
  void setName() {
    milk.setName("Whole Milk");
    assertEquals("Whole Milk", milk.getName());

    assertThrows(IllegalArgumentException.class, () -> milk.setName(null));
  }

  /** Tests the getAmount method. */
  @Test
  void getAmount() {
    assertEquals(1.0, milk.getAmount());
    assertEquals(2.0, bread.getAmount());
  }

  /** Tests the setAmount method with valid and invalid inputs. */
  @Test
  void setAmount() {
    milk.setAmount(2.5);
    assertEquals(2.5, milk.getAmount());

    assertThrows(IllegalArgumentException.class, () -> milk.setAmount(-1.0));
  }

  /** Tests the getMeasurementUnit method. */
  @Test
  void getMeasurementUnit() {
    assertEquals(MeasurementUnit.LITER, milk.getMeasurementUnit());
    assertEquals(MeasurementUnit.PCS, bread.getMeasurementUnit());
  }

  /** Tests the setMeasurementUnit method with valid and invalid inputs. */
  @Test
  void setMeasurementUnit() {
    bread.setMeasurementUnit(MeasurementUnit.KILOGRAM);
    assertEquals(MeasurementUnit.KILOGRAM, bread.getMeasurementUnit());
  }

  /** Tests the getExpirationDate method. */
  @Test
  void getExpirationDate() {
    assertEquals(LocalDate.of(2024, 10, 1), milk.getExpirationDate());
    assertNull(soySauce.getExpirationDate());
  }

  /** Tests the setExpirationDate method with valid and invalid inputs. */
  @Test
  void setExpirationDate() {
    milk.setExpirationDate(LocalDate.of(2024, 12, 31));
    assertEquals(LocalDate.of(2024, 12, 31), milk.getExpirationDate());

    // Test setting past date (should be allowed unless validation is added)
    milk.setExpirationDate(LocalDate.of(2020, 1, 1));
    assertEquals(LocalDate.of(2020, 1, 1), milk.getExpirationDate());
  }

  /** Tests the getPrice method. */
  @Test
  void getPrice() {
    assertEquals(1.5, milk.getPrice());
    assertEquals(3.0, soySauce.getPrice());
  }

  /** Tests the setPrice method with valid and invalid inputs. */
  @Test
  void setPrice() {
    milk.setPrice(2.0);
    assertEquals(2.0, milk.getPrice());

    assertThrows(IllegalArgumentException.class, () -> milk.setPrice(-0.5));
  }

  /** Tests the compareTo method. */
  @Test
  void compareToTest() {
    assertTrue(milk.compareTo(bread) > 0);
    assertTrue(bread.compareTo(milk) < 0);
    assertEquals(0, milk.compareTo(anotherMilk));
  }

  /** Tests the constructor with various inputs. */
  @Test
  void constructorTest() {
    Grocery newGrocery = new Grocery("Eggs", 12.0, MeasurementUnit.PCS, null, 0.2);
    assertEquals("Eggs", newGrocery.getName());
    assertEquals(12.0, newGrocery.getAmount());
    assertEquals(MeasurementUnit.PCS, newGrocery.getMeasurementUnit());
    assertNull(newGrocery.getExpirationDate());
    assertEquals(0.2, newGrocery.getPrice());

    assertThrows(
        IllegalArgumentException.class,
        () -> new Grocery(null, 1.0, MeasurementUnit.LITER, null, 1.0));
  }
}
