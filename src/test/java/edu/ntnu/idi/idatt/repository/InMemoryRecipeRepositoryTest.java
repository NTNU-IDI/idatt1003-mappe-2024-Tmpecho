package edu.ntnu.idi.idatt.repository;

import static org.junit.jupiter.api.Assertions.*;

import edu.ntnu.idi.idatt.model.Grocery;
import edu.ntnu.idi.idatt.model.MeasurementUnit;
import edu.ntnu.idi.idatt.model.Recipe;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/** Tests the InMemoryRecipeRepository for managing recipes. */
class InMemoryRecipeRepositoryTest {
  private InMemoryRecipeRepository recipeRepository;

  @BeforeEach
  void setUp() {
    recipeRepository = new InMemoryRecipeRepository();
  }

  /** Tests that a recipe can be added. */
  @Test
  void addRecipeTest() {
    Recipe recipe = createSampleRecipe("Pancakes");
    recipeRepository.addRecipe(recipe);

    List<Recipe> recipes = recipeRepository.getAllRecipes();

    assertEquals(1, recipes.size());
    assertEquals(recipe, recipes.get(0));
  }

  /** Tests that adding a duplicate recipe throws an exception. */
  @Test
  void addDuplicateRecipeTest() {
    Recipe recipe = createSampleRecipe("Pancakes");
    recipeRepository.addRecipe(recipe);

    assertThrows(IllegalArgumentException.class, () -> recipeRepository.addRecipe(recipe));
  }

  /** Tests that a recipe can be removed. */
  @Test
  void removeRecipeTest() {
    Recipe recipe = createSampleRecipe("Pancakes");
    recipeRepository.addRecipe(recipe);

    recipeRepository.removeRecipe(recipe);
    List<Recipe> recipes = recipeRepository.getAllRecipes();

    assertTrue(recipes.isEmpty());
  }

  /** Tests that removing a non-existent recipe does nothing. */
  @Test
  void removeNonExistentRecipeTest() {
    Recipe recipe = createSampleRecipe("Pancakes");

    recipeRepository.removeRecipe(recipe);

    assertTrue(recipeRepository.getAllRecipes().isEmpty());
  }

  /** Tests finding recipes by name. */
  @Test
  void findRecipesByNameTest() {
    Recipe pancakes = createSampleRecipe("Pancakes");
    Recipe waffles = createSampleRecipe("Waffles");
    recipeRepository.addRecipe(pancakes);
    recipeRepository.addRecipe(waffles);

    List<Recipe> foundRecipes = recipeRepository.findRecipesByName("Pancakes");

    assertEquals(1, foundRecipes.size());
    assertEquals(pancakes, foundRecipes.get(0));
  }

  /** Tests getting all recipes. */
  @Test
  void getAllRecipesTest() {
    Recipe pancakes = createSampleRecipe("Pancakes");
    Recipe waffles = createSampleRecipe("Waffles");
    recipeRepository.addRecipe(pancakes);
    recipeRepository.addRecipe(waffles);

    List<Recipe> allRecipes = recipeRepository.getAllRecipes();

    assertEquals(2, allRecipes.size());
    assertTrue(allRecipes.contains(pancakes));
    assertTrue(allRecipes.contains(waffles));
  }

  private Recipe createSampleRecipe(String name) {
    Grocery flour = new Grocery("Flour", 1.0, MeasurementUnit.KILOGRAM, null, 15.0);
    Grocery milk = new Grocery("Milk", 0.5, MeasurementUnit.LITER, null, 20.0);
    Grocery egg = new Grocery("Egg", 2.0, MeasurementUnit.PCS, null, 3.0);

    return new Recipe(
        name, "Delicious " + name, "Mix ingredients and cook.", List.of(flour, milk, egg));
  }
}
