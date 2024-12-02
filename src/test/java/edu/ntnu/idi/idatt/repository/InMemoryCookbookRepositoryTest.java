package edu.ntnu.idi.idatt.repository;

import static org.junit.jupiter.api.Assertions.*;

import edu.ntnu.idi.idatt.model.Grocery;
import edu.ntnu.idi.idatt.model.MeasurementUnit;
import edu.ntnu.idi.idatt.model.Recipe;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/** Test class for the InMemoryCookbookRepository class. */
class InMemoryCookbookRepositoryTest {
  private CookbookRepository cookbookRepository;

  /** Sets up a new repository before each test. */
  @BeforeEach
  void setUp() {
    cookbookRepository = new InMemoryCookbookRepository();
  }

  /** Tests adding a recipe to the cookbook. */
  @Test
  void addRecipeTest() {
    Recipe recipe = createSampleRecipe("Pancakes");
    cookbookRepository.addRecipe(recipe);

    List<Recipe> recipes = cookbookRepository.getAllRecipes();
    assertEquals(1, recipes.size());
    assertEquals(recipe, recipes.get(0));
  }

  /** Tests adding a duplicate recipe to the cookbook. */
  @Test
  void addDuplicateRecipeTest() {
    Recipe recipe = createSampleRecipe("Pancakes");
    cookbookRepository.addRecipe(recipe);

    assertThrows(IllegalArgumentException.class, () -> cookbookRepository.addRecipe(recipe));
  }

  /** Tests removing a recipe from the cookbook. */
  @Test
  void removeRecipeTest() {
    Recipe recipe = createSampleRecipe("Pancakes");
    cookbookRepository.addRecipe(recipe);

    cookbookRepository.removeRecipe(recipe);

    assertTrue(cookbookRepository.getAllRecipes().isEmpty());
  }

  /** Tests finding recipes by name. */
  @Test
  void findRecipesByNameTest() {
    Recipe recipe1 = createSampleRecipe("Pancakes");
    Recipe recipe2 = createSampleRecipe("Waffles");

    cookbookRepository.addRecipe(recipe1);
    cookbookRepository.addRecipe(recipe2);

    List<Recipe> pancakes = cookbookRepository.findRecipesByName("Pancakes");
    assertEquals(1, pancakes.size());
    assertEquals(recipe1, pancakes.get(0));

    List<Recipe> notFound = cookbookRepository.findRecipesByName("Cake");
    assertTrue(notFound.isEmpty());
  }

  /** Tests getting all recipes. */
  @Test
  void getAllRecipesTest() {
    Recipe recipe1 = createSampleRecipe("Pancakes");
    Recipe recipe2 = createSampleRecipe("Waffles");

    cookbookRepository.addRecipe(recipe1);
    cookbookRepository.addRecipe(recipe2);

    List<Recipe> recipes = cookbookRepository.getAllRecipes();
    assertEquals(2, recipes.size());
    assertTrue(recipes.contains(recipe1));
    assertTrue(recipes.contains(recipe2));
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
