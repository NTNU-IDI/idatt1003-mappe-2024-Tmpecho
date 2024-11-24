package edu.ntnu.idi.idatt.ui;

import edu.ntnu.idi.idatt.controller.GroceryController;
import edu.ntnu.idi.idatt.controller.RecipeController;
import edu.ntnu.idi.idatt.controller.StorageController;
import edu.ntnu.idi.idatt.model.Fridge;
import edu.ntnu.idi.idatt.repository.InMemoryStorageRepository;
import edu.ntnu.idi.idatt.repository.StorageRepository;

/** Base class for all user interfaces. */
public abstract class UserInterface {
  StorageRepository fridgeStorageRepository;
  RecipeController recipeController;
  GroceryController groceryController;
  StorageController storageController;

  /** Initializes UI. */
  public void init() {
    System.out.println("Initializing UI...");

    Fridge fridge = new Fridge("Fridge", 100);

    fridgeStorageRepository = new InMemoryStorageRepository(fridge);

    recipeController = new RecipeController();
    groceryController = new GroceryController();
    storageController = new StorageController(groceryController, fridgeStorageRepository);
  }

  /** Main program loop. Must be implemented by subclasses. */
  public abstract void start();
}
