package edu.ntnu.idi.idatt.controller;

import static org.junit.jupiter.api.Assertions.*;

import edu.ntnu.idi.idatt.model.Storage;
import edu.ntnu.idi.idatt.repository.InMemoryStorageRepository;
import edu.ntnu.idi.idatt.repository.StorageRepository;
import java.time.LocalDate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/** Test class for the StorageController class. */
class StorageControllerTest {
  private StorageController storageController;
  private GroceryController groceryController;
  private StorageRepository storageRepository;
  private Storage storage;

  /** Sets up a new storage controller object before each test. */
  @BeforeEach
  void setUp() {
    storage = new Storage("Pantry", 100, -10, 30);
    groceryController = new GroceryController();
    storageRepository = new InMemoryStorageRepository(storage);
    storageController = new StorageController(groceryController, storageRepository);
  }

  /** Tests adding groceries through the controller. */
  @Test
  void addGroceryTest() {
    storageController.addGrocery("Milk", 10.0, "liter", LocalDate.now().plusDays(5), 1.5);
    assertEquals(1, storageRepository.getAllGroceries().size());
    assertEquals(10.0, storage.getCurrentCapacity());

    // Test adding with invalid unit
    assertThrows(
        IllegalArgumentException.class,
        () -> storageController.addGrocery("Bread", 5.0, "unknownUnit", null, 2.0));
  }

  /** Tests removing groceries through the controller. */
  @Test
  void removeGroceryTest() {
    storageController.addGrocery("Milk", 10.0, "liter", LocalDate.now().plusDays(5), 1.5);
    storageController.removeGrocery("Milk");
    assertEquals(0, storageRepository.getAllGroceries().size());
    assertEquals(0.0, storage.getCurrentCapacity());
  }

  /** Tests listing all groceries. */
  @Test
  void listAllGroceriesTest() {
    storageController.addGrocery("Milk", 10.0, "liter", LocalDate.now().plusDays(5), 1.5);
    storageController.addGrocery("Bread", 5.0, "pcs", LocalDate.now().plusDays(2), 2.0);

    assertEquals(2, storageRepository.getAllGroceries().size());
  }

  /** Tests displaying total value. */
  @Test
  void displayTotalValueTest() {
    storageController.addGrocery("Milk", 10.0, "liter", LocalDate.now().plusDays(5), 1.5);
    storageController.addGrocery("Bread", 5.0, "pcs", LocalDate.now().plusDays(2), 2.0);

    double expectedValue = (10.0 * 1.5) + (5.0 * 2.0);
    assertEquals(expectedValue, storageRepository.calculateTotalValue());
  }

  /** Tests listing expired groceries. */
  @Test
  void listExpiredGroceriesTest() {
    storageController.addGrocery("Milk", 10.0, "liter", LocalDate.now().minusDays(1), 1.5);
    storageController.addGrocery("Bread", 5.0, "pcs", LocalDate.now().plusDays(2), 2.0);

    assertEquals(1, storageRepository.listExpiredGroceries().size());
  }

  /** Tests displaying expired total value. */
  @Test
  void displayExpiredTotalValueTest() {
    storageController.addGrocery("Milk", 10.0, "liter", LocalDate.now().minusDays(1), 1.5);
    storageController.addGrocery("Bread", 5.0, "pcs", LocalDate.now().plusDays(2), 2.0);

    double expectedExpiredValue = 10.0 * 1.5;
    double actualExpiredValue =
        storageRepository.listExpiredGroceries().stream()
            .mapToDouble(groceryController::calculateGroceryValue)
            .sum();

    assertEquals(expectedExpiredValue, actualExpiredValue);
  }
}
