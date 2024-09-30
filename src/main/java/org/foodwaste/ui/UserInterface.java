package org.foodwaste.ui;

import org.foodwaste.controller.GroceryController;
import org.foodwaste.controller.RecipeController;
import org.foodwaste.controller.StorageController;
import org.foodwaste.repository.InMemoryStorageRepository;
import org.foodwaste.repository.StorageRepository;
import org.foodwaste.view.StorageView;

/** Base class for all user interfaces. */
public abstract class UserInterface {
  StorageRepository storageRepository;
  StorageView storageView;
  RecipeController recipeController;
  GroceryController groceryController;
  StorageController storageController;

  /** Initializes UI. */
  public void init() {
    System.out.println("Initializing UI...");

    storageRepository = new InMemoryStorageRepository();
    storageView = new StorageView();
    recipeController = new RecipeController();
    groceryController = new GroceryController(storageRepository, storageView);
    storageController = new StorageController();
  }

  /** Starts base UI loop. */
  public void start() {
    System.out.println("Starting UI... (non-specific implementation)");
  }
}
