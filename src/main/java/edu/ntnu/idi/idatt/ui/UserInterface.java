package edu.ntnu.idi.idatt.ui;

import edu.ntnu.idi.idatt.controller.GroceryController;
import edu.ntnu.idi.idatt.controller.RecipeController;
import edu.ntnu.idi.idatt.controller.StorageController;
import edu.ntnu.idi.idatt.model.Fridge;
import edu.ntnu.idi.idatt.repository.CookbookRepository;
import edu.ntnu.idi.idatt.repository.InMemoryCookbookRepository;
import edu.ntnu.idi.idatt.repository.InMemoryRecipeRepository;
import edu.ntnu.idi.idatt.repository.InMemoryStorageRepository;
import edu.ntnu.idi.idatt.repository.RecipeRepository;
import edu.ntnu.idi.idatt.repository.StorageRepository;

/**
 * Base class for all user interfaces.
 *
 * <p>Contains common functionality for all user interfaces.
 *
 * <p>Subclasses must implement the {@link #start()} method.
 *
 * @see TextMenuInterface
 * @see CliInterface
 */
public abstract class UserInterface {
  StorageRepository fridgeStorageRepository;
  RecipeRepository recipeRepository;
  RecipeController recipeController;
  GroceryController groceryController;
  StorageController storageController;
  Fridge fridge;
  CookbookRepository cookbookRepository;

  /** Initializes UI. */
  public void init() {
    fridge = new Fridge("Fridge", 100);
    cookbookRepository = new InMemoryCookbookRepository();

    fridgeStorageRepository = new InMemoryStorageRepository(fridge);
    recipeRepository = new InMemoryRecipeRepository();

    recipeController = new RecipeController(recipeRepository, cookbookRepository);
    groceryController = new GroceryController();
    storageController = new StorageController(groceryController, fridgeStorageRepository);

    PopulateData populateData = new PopulateData();
    populateData.populateRepositories(fridgeStorageRepository, recipeRepository);
  }

  /** Main program loop. Must be implemented by subclasses. */
  public abstract void start();
}
