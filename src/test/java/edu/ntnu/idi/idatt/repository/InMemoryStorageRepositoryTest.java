package edu.ntnu.idi.idatt.repository;

import static org.junit.jupiter.api.Assertions.*;

import edu.ntnu.idi.idatt.model.Grocery;
import edu.ntnu.idi.idatt.model.MeasurementUnit;
import edu.ntnu.idi.idatt.model.Storage;
import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/** Test class for the InMemoryStorageRepository class. */
class InMemoryStorageRepositoryTest {
  private InMemoryStorageRepository repository;
  private Storage storage;
  private Grocery milk;
  private Grocery bread;
  private Grocery expiredMilk;

  /** Sets up a new storage object and groceries before each test. */
  @BeforeEach
  void setUp() {
    storage = new Storage("Pantry", 100, -10, 30);
    repository = new InMemoryStorageRepository(storage);
    milk = new Grocery("Milk", 10.0, MeasurementUnit.LITER, LocalDate.now().plusDays(5), 1.5);
    bread = new Grocery("Bread", 5.0, MeasurementUnit.PCS, LocalDate.now().plusDays(2), 2.0);
    expiredMilk =
        new Grocery("Expired Milk", 2.0, MeasurementUnit.LITER, LocalDate.now().minusDays(1), 1.5);
  }

  /** Tests adding groceries to the repository. */
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

  /** Tests getting a grocery by name. */
  @Test
  void getGroceryTest() {
    repository.addGrocery(milk);
    Grocery retrievedGrocery = repository.getGrocery("Milk");
    assertEquals(milk, retrievedGrocery);

    assertNull(repository.getGrocery("Coconut"));
  }

  /** Tests removing a grocery by name. */
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

  /** Tests removing a grocery by name and amount. */
  @Test
  void removeGroceryByNameAndAmountTest() {
    repository.addGrocery(milk);
    repository.removeGrocery("Milk", 5.0);

    Grocery retrievedGrocery = repository.getGrocery("Milk");
    assertNotNull(retrievedGrocery);
    assertEquals(5.0, retrievedGrocery.getAmount());

    assertEquals(5.0, storage.getCurrentCapacity());

    // Remove remaining amount
    repository.removeGrocery("Milk");
    assertNull(repository.getGrocery("Milk"));
    assertEquals(0.0, storage.getCurrentCapacity());
  }

  /** Tests removing a grocery with invalid amount. */
  @Test
  void removeGroceryInvalidAmountTest() {
    repository.addGrocery(milk);

    // Negative amount
    assertThrows(IllegalArgumentException.class, () -> repository.removeGrocery("Milk", -5.0));

    // Amount exceeds available
    assertThrows(IllegalArgumentException.class, () -> repository.removeGrocery("Milk", 15.0));
  }

  /** Tests listing all groceries. */
  @Test
  void getAllGroceriesTest() {
    repository.addGrocery(milk);
    repository.addGrocery(bread);
    List<Grocery> groceries = repository.getAllGroceries();

    assertEquals(2, groceries.size());
    assertEquals("Bread", groceries.get(0).getName());
    assertEquals("Milk", groceries.get(1).getName()); // Sorted by name
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

  /** Tests calculating total value of all groceries. */
  @Test
  void calculateTotalValueTest() {
    repository.addGrocery(milk);
    repository.addGrocery(bread);
    double totalValue = repository.calculateTotalValue();

    double expectedValue =
        (milk.getPrice() * milk.getAmount()) + (bread.getPrice() * bread.getAmount());
    assertEquals(expectedValue, totalValue);
  }
}
