package edu.ntnu.idi.idatt.ui;

import edu.ntnu.idi.idatt.controller.GroceryController;
import edu.ntnu.idi.idatt.controller.RecipeController;
import edu.ntnu.idi.idatt.controller.StorageController;
import edu.ntnu.idi.idatt.model.Cookbook;
import edu.ntnu.idi.idatt.model.Fridge;
import edu.ntnu.idi.idatt.repository.InMemoryRecipeRepository;
import edu.ntnu.idi.idatt.repository.InMemoryStorageRepository;
import edu.ntnu.idi.idatt.repository.RecipeRepository;
import edu.ntnu.idi.idatt.repository.StorageRepository;

/** Base class for all user interfaces. */
public abstract class UserInterface {
  StorageRepository fridgeStorageRepository;
  RecipeRepository recipeRepository;
  RecipeController recipeController;
  GroceryController groceryController;
  StorageController storageController;
  Fridge fridge;
  Cookbook cookbook;

  /** Initializes UI. */
  public void init() {
    fridge = new Fridge("Fridge", 100);
    cookbook = new Cookbook();

    fridgeStorageRepository = new InMemoryStorageRepository(fridge);
    recipeRepository = new InMemoryRecipeRepository();

    recipeController = new RecipeController(recipeRepository, cookbook);
    groceryController = new GroceryController();
    storageController = new StorageController(groceryController, fridgeStorageRepository);
  }

  /** Main program loop. Must be implemented by subclasses. */
  public abstract void start();
}
