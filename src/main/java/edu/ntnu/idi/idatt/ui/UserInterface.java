package edu.ntnu.idi.idatt.ui;

import edu.ntnu.idi.idatt.controller.GroceryController;
import edu.ntnu.idi.idatt.controller.RecipeController;
import edu.ntnu.idi.idatt.controller.StorageController;
import edu.ntnu.idi.idatt.model.Fridge;
import edu.ntnu.idi.idatt.repository.InMemoryStorageRepository;
import edu.ntnu.idi.idatt.repository.StorageRepository;

/** Base class for all user interfaces. */
public abstract class UserInterface {
  StorageRepository storageRepository;
  Fridge fridge;
  RecipeController recipeController;
  GroceryController groceryController;
  StorageController storageController;

  /** Initializes UI. */
  public void init() {
    System.out.println("Initializing UI...");

    fridge = new Fridge("Fridge", 100, 1, 8);

    storageRepository = new InMemoryStorageRepository(fridge);

    recipeController = new RecipeController();
    groceryController = new GroceryController(storageRepository);
    storageController = new StorageController();
  }

  /** Starts base UI loop. */
  public void start() { // Maybe this should be abstract?
    System.out.println("Starting UI... (non-specific implementation)");
  }
}
