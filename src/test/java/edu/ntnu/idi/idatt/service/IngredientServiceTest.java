package edu.ntnu.idi.idatt.service;

import static org.junit.jupiter.api.Assertions.*;

import edu.ntnu.idi.idatt.model.Grocery;
import edu.ntnu.idi.idatt.model.MeasurementUnit;
import edu.ntnu.idi.idatt.model.Recipe;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/** Test class for the IngredientService. */
class IngredientServiceTest {
  private Recipe recipe;
  private List<Grocery> availableGroceries;

  /** Sets up a recipe and available groceries before each test. */
  @BeforeEach
  void setUp() {
    recipe =
        new Recipe(
            "Pancakes",
            "Classic breakfast dish",
            "Mix eggs, milk, and flour. Cook in a frying pan.",
            List.of(
                new Grocery("eggs", 3.0, MeasurementUnit.PCS, null, null),
                new Grocery("milk", 1.0, MeasurementUnit.LITER, null, null),
                new Grocery("flour", 0.5, MeasurementUnit.KILOGRAM, null, null)));

    availableGroceries =
        List.of(
            new Grocery("eggs", 4.0, MeasurementUnit.PCS, null, null),
            new Grocery("milk", 1.0, MeasurementUnit.LITER, null, null),
            new Grocery("flour", 0.4, MeasurementUnit.KILOGRAM, null, null));
  }

  /** Tests that not all ingredients are available in sufficient quantities. */
  @Test
  void hasAllIngredientsTestFalse() {
    assertFalse(IngredientService.hasAllIngredients(recipe, availableGroceries));
  }

  /** Tests that all ingredients are available in sufficient quantities. */
  @Test
  void hasAllIngredientsTestTrue() {
    availableGroceries.get(2).setAmount(0.5);
    assertTrue(IngredientService.hasAllIngredients(recipe, availableGroceries));
  }

  /** Tests that an empty grocery list returns false. */
  @Test
  void hasAllIngredientsEmptyGroceriesTest() {
    assertFalse(IngredientService.hasAllIngredients(recipe, List.of()));
  }

  /** Tests that a recipe with no ingredients returns true. */
  @Test
  void hasAllIngredientsNoRecipeIngredientsTest() {
    Recipe emptyRecipe = new Recipe("Empty Recipe", "No ingredients", "N/A", List.of());
    assertTrue(IngredientService.hasAllIngredients(emptyRecipe, availableGroceries));
  }
}
