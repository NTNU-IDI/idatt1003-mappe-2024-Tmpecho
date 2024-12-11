package edu.ntnu.idi.idatt.service;

import static org.junit.jupiter.api.Assertions.*;

import edu.ntnu.idi.idatt.model.Grocery;
import edu.ntnu.idi.idatt.model.MeasurementUnit;
import edu.ntnu.idi.idatt.model.Recipe;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/** Tests the RecipeSuggestionService for suggesting the best matching recipe. */
class RecipeSuggestionServiceTest {
  private RecipeSuggestionService suggestionService;
  private List<Grocery> availableGroceries;

  @BeforeEach
  void setUp() {
    suggestionService = new RecipeSuggestionService();
    availableGroceries =
        List.of(
            new Grocery("flour", 2.0, MeasurementUnit.KILOGRAM, null, 15.0),
            new Grocery("milk", 1.0, MeasurementUnit.LITER, null, 20.0),
            new Grocery("egg", 3.0, MeasurementUnit.PCS, null, 3.0),
            new Grocery("butter", 0.5, MeasurementUnit.KILOGRAM, null, 50.0));
  }

  /** Tests suggesting a recipe that perfectly matches available ingredients. */
  @Test
  void suggestRecipePerfectMatchTest() {
    Recipe recipe =
        new Recipe(
            "Pancakes",
            "Delicious pancakes",
            "Mix and cook.",
            List.of(
                new Grocery("flour", 1.0, MeasurementUnit.KILOGRAM, null, null),
                new Grocery("milk", 0.5, MeasurementUnit.LITER, null, null),
                new Grocery("egg", 2.0, MeasurementUnit.PCS, null, null)));

    Recipe result = suggestionService.suggestRecipe(List.of(recipe), availableGroceries);

    assertEquals(recipe, result);
  }

  /** Tests suggesting a recipe with partial matches. */
  @Test
  void suggestRecipePartialMatchTest() {
    Recipe recipe1 =
        new Recipe(
            "Pancakes",
            "Delicious pancakes",
            "Mix and cook.",
            List.of(
                new Grocery("flour", 1.0, MeasurementUnit.KILOGRAM, null, null),
                new Grocery("milk", 0.5, MeasurementUnit.LITER, null, null),
                new Grocery("egg", 2.0, MeasurementUnit.PCS, null, null)));

    Recipe recipe2 =
        new Recipe(
            "Omelette",
            "Tasty omelette",
            "Cook eggs and butter.",
            List.of(
                new Grocery("egg", 2.0, MeasurementUnit.PCS, null, null),
                new Grocery("butter", 0.2, MeasurementUnit.KILOGRAM, null, null),
                new Grocery("cheese", 0.5, MeasurementUnit.KILOGRAM, null, null)));

    Recipe result = suggestionService.suggestRecipe(List.of(recipe1, recipe2), availableGroceries);

    assertEquals(recipe1, result);
  }

  /** Tests that no recipe is suggested when none match. */
  @Test
  void suggestRecipeNoMatchTest() {
    Recipe recipe =
        new Recipe(
            "Salad",
            "Fresh salad",
            "Mix veggies.",
            List.of(
                new Grocery("lettuce", 1.0, MeasurementUnit.KILOGRAM, null, null),
                new Grocery("tomato", 0.5, MeasurementUnit.KILOGRAM, null, null)));

    Recipe result = suggestionService.suggestRecipe(List.of(recipe), availableGroceries);

    assertNull(result);
  }

  /** Tests tie-breaking when two recipes have the same match score. */
  @Test
  void suggestRecipeTieBreakTest() {
    Recipe recipe1 =
        new Recipe(
            "Recipe1",
            "Recipe with equal match score",
            "Do something.",
            List.of(
                new Grocery("flour", 1.0, MeasurementUnit.KILOGRAM, null, null),
                new Grocery("milk", 0.5, MeasurementUnit.LITER, null, null)));

    Recipe recipe2 =
        new Recipe(
            "Recipe2",
            "Another recipe with equal match score",
            "Do something else.",
            List.of(
                new Grocery("butter", 0.5, MeasurementUnit.KILOGRAM, null, null),
                new Grocery("egg", 2.0, MeasurementUnit.PCS, null, null)));

    Recipe result = suggestionService.suggestRecipe(List.of(recipe1, recipe2), availableGroceries);

    assertTrue(result == recipe1 || result == recipe2);
  }

  /** Tests suggesting a recipe when there are no recipes. */
  @Test
  void suggestRecipeEmptyRecipesListTest() {
    Recipe result = suggestionService.suggestRecipe(List.of(), availableGroceries);

    assertNull(result);
  }

  /** Tests suggesting a recipe when groceries are null. */
  @Test
  void suggestRecipeWithNullIngredientsTest() {
    Recipe recipe =
        new Recipe(
            "Milk Only",
            "Just milk",
            "Drink it.",
            List.of(new Grocery("milk", 1.0, MeasurementUnit.LITER, null, null)));

    List<Grocery> noGroceries = List.of();

    assertDoesNotThrow(() -> suggestionService.suggestRecipe(List.of(recipe), noGroceries));
    assertNull(suggestionService.suggestRecipe(List.of(recipe), noGroceries));
  }
}
