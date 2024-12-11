package edu.ntnu.idi.idatt.controller;

import static org.junit.jupiter.api.Assertions.*;

import edu.ntnu.idi.idatt.model.Storage;
import edu.ntnu.idi.idatt.repository.InMemoryStorageRepository;
import edu.ntnu.idi.idatt.repository.StorageRepository;
import java.time.LocalDate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/** Tests the StorageController for managing groceries in storage. */
class StorageControllerTest {
  private StorageController storageController;
  private StorageRepository storageRepository;
  private Storage storage;

  @BeforeEach
  void setUp() {
    storage = new Storage("Pantry", 100, -10, 30);
    GroceryController groceryController = new GroceryController();
    storageRepository = new InMemoryStorageRepository(storage);
    storageController = new StorageController(groceryController, storageRepository);
  }

  /** Tests adding a grocery to storage. */
  @Test
  void addGroceryTest() {
    storageController.addGrocery("Milk", 10.0, "liter", LocalDate.now().plusDays(5), 1.5);

    assertEquals(1, storageRepository.getAllGroceries().size());
    assertEquals(10.0, storage.getCurrentCapacity());
  }

  /** Tests adding a grocery with an invalid unit. */
  @Test
  void addGroceryInvalidUnitTest() {
    assertThrows(
        IllegalArgumentException.class,
        () -> storageController.addGrocery("Bread", 5.0, "unknownUnit", null, 2.0));
  }

  /** Tests adding a grocery with a negative amount. */
  @Test
  void addGroceryNegativeAmountTest() {
    assertThrows(
        IllegalArgumentException.class,
        () -> storageController.addGrocery("Sugar", -5.0, "kg", null, 10.0));
  }

  /** Tests removing a grocery from storage. */
  @Test
  void removeGroceryTest() {
    storageController.addGrocery("Milk", 10.0, "liter", LocalDate.now().plusDays(5), 1.5);
    storageController.removeGrocery("Milk");

    assertEquals(0, storageRepository.getAllGroceries().size());
    assertEquals(0.0, storage.getCurrentCapacity());
  }

  /** Tests removing a non-existent grocery has no effect. */
  @Test
  void removeNonExistentGroceryTest() {
    storageController.addGrocery("Milk", 10.0, "liter", null, 1.5);
    storageController.removeGrocery("NonExistent");

    assertEquals(1, storageRepository.getAllGroceries().size());
    assertNotNull(storageRepository.getGrocery("Milk"));
  }

  /** Tests listing all groceries in storage. */
  @Test
  void listAllGroceriesTest() {
    storageController.addGrocery("Milk", 10.0, "liter", LocalDate.now().plusDays(5), 1.5);
    storageController.addGrocery("Bread", 5.0, "pcs", LocalDate.now().plusDays(2), 2.0);

    boolean result = storageController.listAllGroceries();

    assertTrue(result);
    assertEquals(2, storageRepository.getAllGroceries().size());
  }

  /** Tests listing all groceries when storage is empty. */
  @Test
  void listAllGroceriesEmptyTest() {
    boolean result = storageController.listAllGroceries();

    assertFalse(result);
  }

  /** Tests displaying the total value of groceries in storage. */
  @Test
  void displayTotalValueTest() {
    storageController.addGrocery("Milk", 10.0, "liter", LocalDate.now().plusDays(5), 1.5);
    storageController.addGrocery("Bread", 5.0, "pcs", LocalDate.now().plusDays(2), 2.0);

    double calculatedValue = storageRepository.calculateTotalValue();
    double expectedValue = (10.0 * 1.5) + (5.0 * 2.0);

    assertEquals(expectedValue, calculatedValue);
  }

  /** Tests listing expired groceries. */
  @Test
  void listExpiredGroceriesTest() {
    storageController.addGrocery("Milk", 10.0, "liter", LocalDate.now().minusDays(1), 1.5);
    storageController.addGrocery("Bread", 5.0, "pcs", LocalDate.now().plusDays(2), 2.0);

    boolean result = storageController.listExpiredGroceries();

    assertTrue(result);
    assertEquals(1, storageRepository.listExpiredGroceries().size());
  }

  /** Tests listing expired groceries when none are expired. */
  @Test
  void listExpiredGroceriesNoneExpiredTest() {
    storageController.addGrocery("Bread", 5.0, "pcs", LocalDate.now().plusDays(2), 2.0);

    boolean result = storageController.listExpiredGroceries();

    assertFalse(result);
  }

  /** Tests checking if a grocery is in storage. */
  @Test
  void groceryInStorageTest() {
    storageController.addGrocery("Milk", 10.0, "liter", null, 1.5);

    assertTrue(storageController.groceryInStorage("Milk"));
    assertFalse(storageController.groceryInStorage("NonExistent"));
  }

  /** Tests displaying storage details does not throw any exceptions. */
  @Test
  void displayStorageDetailsTest() {
    assertDoesNotThrow(() -> storageController.displayStorageDetails(storage));
  }
}
