package edu.ntnu.idi.idatt.repository;

import static org.junit.jupiter.api.Assertions.*;

import edu.ntnu.idi.idatt.model.Grocery;
import edu.ntnu.idi.idatt.model.MeasurementUnit;
import edu.ntnu.idi.idatt.model.Storage;
import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/** Tests the InMemoryStorageRepository functionality for managing groceries. */
class InMemoryStorageRepositoryTest {
  private InMemoryStorageRepository repository;
  private Storage storage;
  private Grocery milk;
  private Grocery bread;
  private Grocery expiredMilk;

  @BeforeEach
  void setUp() {
    storage = new Storage("Pantry", 100, -10, 30);
    repository = new InMemoryStorageRepository(storage);
    milk = new Grocery("Milk", 10.0, MeasurementUnit.LITER, LocalDate.now().plusDays(5), 1.5);
    bread = new Grocery("Bread", 5.0, MeasurementUnit.PCS, LocalDate.now().plusDays(2), 2.0);
    expiredMilk =
        new Grocery("Expired Milk", 2.0, MeasurementUnit.LITER, LocalDate.now().minusDays(1), 1.5);
  }

  /** Tests that groceries can be added and stored correctly. */
  @Test
  void addGroceryTest() {
    repository.addGrocery(milk);
    repository.addGrocery(bread);

    List<Grocery> groceries = repository.getAllGroceries();
    assertEquals(2, groceries.size());
    assertTrue(groceries.contains(milk));
    assertTrue(groceries.contains(bread));
    assertEquals(15.0, storage.getCurrentCapacity());
  }

  /** Tests that adding a null grocery throws a NullPointerException. */
  @Test
  void addNullGroceryTest() {
    Grocery nullGrocery = null;

    assertThrows(NullPointerException.class, () -> repository.addGrocery(nullGrocery));
  }

  /** Tests retrieving a grocery by name. */
  @Test
  void getGroceryTest() {
    repository.addGrocery(milk);

    Grocery retrievedGrocery = repository.getGrocery("Milk");
    Grocery notFound = repository.getGrocery("Coconut");

    assertEquals(milk, retrievedGrocery);
    assertNull(notFound);
  }

  /** Tests removing a grocery by its name. */
  @Test
  void removeGroceryByNameTest() {
    repository.addGrocery(milk);
    repository.addGrocery(bread);

    repository.removeGrocery("Milk");
    List<Grocery> groceries = repository.getAllGroceries();

    assertEquals(1, groceries.size());
    assertFalse(groceries.contains(milk));
    assertTrue(groceries.contains(bread));
    assertEquals(5.0, storage.getCurrentCapacity());
  }

  /** Tests that removing a non-existent grocery does not affect storage. */
  @Test
  void removeNonExistentGroceryTest() {
    repository.addGrocery(milk);

    repository.removeGrocery("NonExistent");
    List<Grocery> groceries = repository.getAllGroceries();

    assertEquals(1, groceries.size());
    assertTrue(groceries.contains(milk));
  }

  /** Tests removing a specific amount of a grocery. */
  @Test
  void removeGroceryByNameAndAmountTest() {
    repository.addGrocery(milk);

    repository.removeGrocery("Milk", 5.0);
    Grocery retrievedGrocery = repository.getGrocery("Milk");
    repository.removeGrocery("Milk");

    assertNotNull(retrievedGrocery);
    assertEquals(5.0, retrievedGrocery.getAmount());
    assertNull(repository.getGrocery("Milk"));
    assertEquals(0.0, storage.getCurrentCapacity());
  }

  /** Tests that removing an invalid amount (negative or too large) throws exceptions. */
  @Test
  void removeGroceryInvalidAmountTest() {
    repository.addGrocery(milk);

    assertThrows(IllegalArgumentException.class, () -> repository.removeGrocery("Milk", -5.0));
    assertThrows(IllegalArgumentException.class, () -> repository.removeGrocery("Milk", 15.0));
  }

  /** Tests getting all groceries sorted by name. */
  @Test
  void getAllGroceriesTest() {
    repository.addGrocery(milk);
    repository.addGrocery(bread);

    List<Grocery> groceries = repository.getAllGroceries();

    assertEquals(2, groceries.size());
    assertEquals("Bread", groceries.get(0).getName());
    assertEquals("Milk", groceries.get(1).getName());
  }

  /** Tests listing expired groceries. */
  @Test
  void listExpiredGroceriesTest() {
    repository.addGrocery(milk);
    repository.addGrocery(expiredMilk);

    List<Grocery> expiredGroceries = repository.listExpiredGroceries();
    assertEquals(1, expiredGroceries.size());
    assertEquals(expiredMilk, expiredGroceries.get(0));
  }

  /** Tests calculating the total value of all groceries. */
  @Test
  void calculateTotalValueTest() {
    repository.addGrocery(milk);
    repository.addGrocery(bread);

    double expectedValue =
        (milk.getPrice() * milk.getAmount()) + (bread.getPrice() * bread.getAmount());
    double totalValue = repository.calculateTotalValue();

    assertEquals(expectedValue, totalValue);
  }

  /** Tests that total value is zero if no groceries are present. */
  @Test
  void calculateTotalValueEmptyStorageTest() {
    double totalValue = repository.calculateTotalValue();
    assertEquals(0.0, totalValue);
  }
}
