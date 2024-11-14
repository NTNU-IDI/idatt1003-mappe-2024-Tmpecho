package edu.ntnu.idi.idatt.ui;

import edu.ntnu.idi.idatt.controller.GroceryController;
import edu.ntnu.idi.idatt.controller.RecipeController;
import edu.ntnu.idi.idatt.controller.StorageController;
//import edu.ntnu.idi.idatt.factory.StorageFactory;
import edu.ntnu.idi.idatt.model.Cupboard;
import edu.ntnu.idi.idatt.model.Fridge;
import edu.ntnu.idi.idatt.model.Storage;
import edu.ntnu.idi.idatt.repository.InMemoryStorageRepository;
import edu.ntnu.idi.idatt.repository.StorageRepository;

/** Base class for all user interfaces. */
public abstract class UserInterface {
  StorageRepository fridgeStorageRepository;
  StorageRepository cupboardStorageRepository;
  StorageRepository otherStorageRepository;
//  StorageFactory storageFactory;
  RecipeController recipeController;
  GroceryController groceryController;
  StorageController storageController;

  /** Initializes UI. */
  public void init() {
    System.out.println("Initializing UI...");

    Fridge fridge = new Fridge("Fridge", 100, 1, 8);
    Cupboard cupboard = new Cupboard("Cupboard", 100, 15, 25);
    Storage otherStorage = new Storage("Other storage", 100, -100, 100);

    fridgeStorageRepository = new InMemoryStorageRepository(fridge);
    cupboardStorageRepository = new InMemoryStorageRepository(cupboard);
    otherStorageRepository = new InMemoryStorageRepository(otherStorage);

//    storageFactory = new StorageFactory();

    recipeController = new RecipeController();
    groceryController = new GroceryController();
    storageController = new StorageController(groceryController, otherStorageRepository);
  }

  /** Starts base UI loop. */
  public abstract void start();
}
