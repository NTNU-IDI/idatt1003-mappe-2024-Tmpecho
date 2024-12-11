package edu.ntnu.idi.idatt.controller;

import static org.junit.jupiter.api.Assertions.*;

import edu.ntnu.idi.idatt.model.Grocery;
import edu.ntnu.idi.idatt.model.MeasurementUnit;
import edu.ntnu.idi.idatt.model.Recipe;
import edu.ntnu.idi.idatt.repository.CookbookRepository;
import edu.ntnu.idi.idatt.repository.InMemoryCookbookRepository;
import edu.ntnu.idi.idatt.repository.InMemoryRecipeRepository;
import edu.ntnu.idi.idatt.repository.RecipeRepository;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/** Tests the RecipeController for adding, removing, and suggesting recipes. */
class RecipeControllerTest {
  private RecipeController recipeController;
  private RecipeRepository recipeRepository;
  private CookbookRepository cookbookRepository;

  @BeforeEach
  void setUp() {
    recipeRepository = new InMemoryRecipeRepository();
    cookbookRepository = new InMemoryCookbookRepository();
    recipeController = new RecipeController(recipeRepository, cookbookRepository);
  }

  /** Tests adding a new recipe. */
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

  /** Tests that adding a duplicate recipe throws an exception. */
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

  /** Tests removing an existing recipe. */
  @Test
  void removeRecipeTest() {
    Recipe recipe = createSampleRecipe("Pancakes");
    recipeController.addRecipe(
        recipe.getName(),
        recipe.getDescription(),
        recipe.getInstructions(),
        recipe.getIngredients());

    recipeController.removeRecipe(recipe);

    List<Recipe> recipes = recipeRepository.getAllRecipes();
    assertEquals(0, recipes.size());
  }

  /** Tests removing a non-existent recipe has no effect. */
  @Test
  void removeNonExistentRecipeTest() {
    Recipe recipe = createSampleRecipe("NonExistent");

    recipeController.removeRecipe(recipe);

    List<Recipe> recipes = recipeRepository.getAllRecipes();
    assertTrue(recipes.isEmpty());
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

    boolean saved = recipeController.saveRecipeToCookbook(recipe);
    List<Recipe> cookbookRecipes = cookbookRepository.getAllRecipes();

    assertTrue(saved);
    assertEquals(1, cookbookRecipes.size());
    assertEquals("Pancakes", cookbookRecipes.get(0).getName());
  }

  /** Tests that saving a duplicate recipe to the cookbook returns false. */
  @Test
  void saveDuplicateRecipeToCookbookTest() {
    Recipe recipe = createSampleRecipe("Pancakes");
    recipeController.addRecipe(
        recipe.getName(),
        recipe.getDescription(),
        recipe.getInstructions(),
        recipe.getIngredients());
    recipeController.saveRecipeToCookbook(recipe);

    boolean savedAgain = recipeController.saveRecipeToCookbook(recipe);
    List<Recipe> cookbookRecipes = cookbookRepository.getAllRecipes();

    assertFalse(savedAgain);
    assertEquals(1, cookbookRecipes.size());
  }

  /** Tests displaying recipes in the cookbook. */
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

    List<Recipe> cookbookRecipes = cookbookRepository.getAllRecipes();

    assertEquals(2, cookbookRecipes.size());
  }

  /** Tests checking if a recipe can be made with the available ingredients. */
  @Test
  void canMakeRecipeTest() {
    Recipe recipe = createSampleRecipe("Pancakes");
    recipeController.addRecipe(
        recipe.getName(),
        recipe.getDescription(),
        recipe.getInstructions(),
        recipe.getIngredients());

    List<Grocery> availableGroceries =
        List.of(
            new Grocery("Flour", 1.0, MeasurementUnit.KILOGRAM, null, null),
            new Grocery("Milk", 0.5, MeasurementUnit.LITER, null, null),
            new Grocery("Egg", 3.0, MeasurementUnit.PCS, null, null));

    assertDoesNotThrow(() -> recipeController.canMakeRecipe(recipe, availableGroceries));
  }

  /** Tests suggesting a recipe based on available ingredients. */
  @Test
  void suggestRecipeTest() {
    Recipe recipe = createSampleRecipe("Pancakes");
    recipeController.addRecipe(
        recipe.getName(),
        recipe.getDescription(),
        recipe.getInstructions(),
        recipe.getIngredients());

    List<Grocery> availableGroceries =
        List.of(
            new Grocery("flour", 1.0, MeasurementUnit.KILOGRAM, null, null),
            new Grocery("milk", 1.0, MeasurementUnit.LITER, null, null),
            new Grocery("egg", 3.0, MeasurementUnit.PCS, null, null));

    Recipe suggested = recipeController.suggestRecipe(availableGroceries);

    assertNotNull(suggested, "Suggested recipe should not be null");
    assertEquals("Pancakes", suggested.getName());
  }

  /** Tests suggesting a recipe when no match is found. */
  @Test
  void suggestRecipeNoMatchTest() {
    Recipe recipe = createSampleRecipe("Pancakes");
    recipeController.addRecipe(
        recipe.getName(),
        recipe.getDescription(),
        recipe.getInstructions(),
        recipe.getIngredients());

    List<Grocery> availableGroceries =
        List.of(new Grocery("Tomato", 2.0, MeasurementUnit.PCS, null, null));

    Recipe suggested = recipeController.suggestRecipe(availableGroceries);

    assertNull(suggested);
  }

  private Recipe createSampleRecipe(String name) {
    Grocery flour = new Grocery("Flour", 1.0, MeasurementUnit.KILOGRAM, null, 15.0);
    Grocery milk = new Grocery("Milk", 0.5, MeasurementUnit.LITER, null, 20.0);
    Grocery egg = new Grocery("Egg", 2.0, MeasurementUnit.PCS, null, 3.0);

    return new Recipe(
        name, "Delicious " + name, "Mix ingredients and cook.", List.of(flour, milk, egg));
  }
}
