package edu.ntnu.idi.idatt.controller;

import static org.junit.jupiter.api.Assertions.*;

import edu.ntnu.idi.idatt.model.Cookbook;
import edu.ntnu.idi.idatt.model.Grocery;
import edu.ntnu.idi.idatt.model.MeasurementUnit;
import edu.ntnu.idi.idatt.model.Recipe;
import edu.ntnu.idi.idatt.repository.InMemoryRecipeRepository;
import edu.ntnu.idi.idatt.repository.RecipeRepository;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/** Test class for the RecipeController class. */
class RecipeControllerTest {
  private RecipeController recipeController;
  private RecipeRepository recipeRepository;
  private Cookbook cookbook;

  /** Sets up the test environment before each test. */
  @BeforeEach
  void setUp() {
    recipeRepository = new InMemoryRecipeRepository();
    cookbook = new Cookbook();
    recipeController = new RecipeController(recipeRepository, cookbook);
  }

  /** Tests adding a recipe to the repository. */
  @Test
  void addRecipeTest() {
    Recipe recipe = createSampleRecipe("Pancakes");

    recipeController.addRecipe(
        recipe.getName(),
        recipe.getDescription(),
        recipe.getInstructions(),
        recipe.getIngredients());

    List<Recipe> recipes = recipeRepository.getAllRecipes();

    assertEquals(1, recipes.size());
    assertEquals("Pancakes", recipes.get(0).getName());
  }

  /** Tests adding a duplicate recipe to the repository. */
  @Test
  void addDuplicateRecipeTest() {
    Recipe recipe = createSampleRecipe("Pancakes");

    recipeController.addRecipe(
        recipe.getName(),
        recipe.getDescription(),
        recipe.getInstructions(),
        recipe.getIngredients());

    assertThrows(
        IllegalArgumentException.class,
        () ->
            recipeController.addRecipe(
                recipe.getName(),
                recipe.getDescription(),
                recipe.getInstructions(),
                recipe.getIngredients()));
  }

  /** Tests removing a recipe from the repository. */
  @Test
  void removeRecipeTest() {
    Recipe recipe = createSampleRecipe("Pancakes");

    assertEquals(0, recipeRepository.getAllRecipes().size());

    recipeController.addRecipe(
        recipe.getName(),
        recipe.getDescription(),
        recipe.getInstructions(),
        recipe.getIngredients());

    assertEquals(1, recipeRepository.getAllRecipes().size());

    recipeController.removeRecipe(recipe);

    List<Recipe> recipes = recipeRepository.getAllRecipes();

    assertEquals(0, recipes.size());
  }

  /** Tests saving a recipe to the cookbook. */
  @Test
  void saveRecipeToCookbookTest() {
    Recipe recipe = createSampleRecipe("Pancakes");

    recipeController.addRecipe(
        recipe.getName(),
        recipe.getDescription(),
        recipe.getInstructions(),
        recipe.getIngredients());

    assertEquals(0, cookbook.getRecipes().size());

    recipeController.saveRecipeToCookbook(recipe);

    List<Recipe> cookbookRecipes = cookbook.getRecipes();

    assertEquals(1, cookbookRecipes.size());
    assertEquals("Pancakes", cookbookRecipes.get(0).getName());
  }

  /** Tests saving a duplicate recipe to the cookbook. */
  @Test
  void saveDuplicateRecipeToCookbookTest() {
    Recipe recipe = createSampleRecipe("Pancakes");

    recipeController.addRecipe(
        recipe.getName(),
        recipe.getDescription(),
        recipe.getInstructions(),
        recipe.getIngredients());
    recipeController.saveRecipeToCookbook(recipe);

    assertDoesNotThrow(() -> recipeController.saveRecipeToCookbook(recipe));

    List<Recipe> cookbookRecipes = cookbook.getRecipes();

    assertEquals(1, cookbookRecipes.size());
  }

  /** Tests displaying recipes from the cookbook. */
  @Test
  void displayRecipesInCookbookTest() {
    Recipe recipe1 = createSampleRecipe("Pancakes");
    Recipe recipe2 = createSampleRecipe("Waffles");

    recipeController.addRecipe(
        recipe1.getName(),
        recipe1.getDescription(),
        recipe1.getInstructions(),
        recipe1.getIngredients());
    recipeController.addRecipe(
        recipe2.getName(),
        recipe2.getDescription(),
        recipe2.getInstructions(),
        recipe2.getIngredients());
    recipeController.saveRecipeToCookbook(recipe1);
    recipeController.saveRecipeToCookbook(recipe2);

    List<Recipe> cookbookRecipes = cookbook.getRecipes();

    assertEquals(2, cookbookRecipes.size());
  }

  /** Helper method to create a sample recipe. */
  private Recipe createSampleRecipe(String name) {
    Grocery flour = new Grocery("Flour", 1.0, MeasurementUnit.KILOGRAM, null, 15.0);
    Grocery milk = new Grocery("Milk", 0.5, MeasurementUnit.LITER, null, 20.0);
    Grocery egg = new Grocery("Egg", 2.0, MeasurementUnit.PCS, null, 3.0);

    return new Recipe(
        name,
        "Delicious " + name,
        "Mix ingredients and cook.",
        List.of(Map.entry(flour, 1.0), Map.entry(milk, 0.5), Map.entry(egg, 2.0)));
  }
}
