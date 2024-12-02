package edu.ntnu.idi.idatt.util;

import edu.ntnu.idi.idatt.model.Grocery;
import edu.ntnu.idi.idatt.model.MeasurementUnit;
import edu.ntnu.idi.idatt.model.Recipe;
import edu.ntnu.idi.idatt.repository.RecipeRepository;
import edu.ntnu.idi.idatt.repository.StorageRepository;
import java.time.LocalDate;
import java.util.List;

/** Utility class to populate repositories with initial data. */
public class PopulateData {
  /**
   * Populates the repositories with initial data.
   *
   * @param fridgeStorageRepository The storage repository for groceries
   * @param recipeRepository The recipe repository
   */
  public void populateRepositories(
      StorageRepository fridgeStorageRepository, RecipeRepository recipeRepository) {
    fridgeStorageRepository.addGrocery(
        new Grocery("milk", 2.0, MeasurementUnit.LITER, LocalDate.now().plusDays(7), 20.0));
    fridgeStorageRepository.addGrocery(
        new Grocery("eggs", 12.0, MeasurementUnit.PCS, LocalDate.now().plusDays(14), 5.0));
    fridgeStorageRepository.addGrocery(
        new Grocery("butter", 1.0, MeasurementUnit.KILOGRAM, LocalDate.now().plusDays(30), 70.0));
    fridgeStorageRepository.addGrocery(
        new Grocery("cheese", 0.5, MeasurementUnit.KILOGRAM, LocalDate.now().plusDays(60), 100.0));
    fridgeStorageRepository.addGrocery(
        new Grocery("bread", 1.0, MeasurementUnit.PCS, LocalDate.now().minusDays(7), 25.0));

    recipeRepository.addRecipe(
        new Recipe(
            "pancakes",
            "Classic breakfast dish",
            "Mix eggs, milk, and flour. Cook in a frying pan.",
            List.of(
                new Grocery("Eggs", 3.0, MeasurementUnit.PCS, null, null),
                new Grocery("Milk", 0.5, MeasurementUnit.LITER, null, null),
                new Grocery("Butter", 0.1, MeasurementUnit.KILOGRAM, null, null))));

    recipeRepository.addRecipe(
        new Recipe(
            "cheese omelette",
            "Simple and delicious",
            "Beat eggs, add cheese, and cook in a pan.",
            List.of(
                new Grocery("Eggs", 2.0, MeasurementUnit.PCS, null, null),
                new Grocery("Cheese", 0.1, MeasurementUnit.KILOGRAM, null, null))));
  }
}
