package edu.ntnu.idi.idatt.repository;

import edu.ntnu.idi.idatt.model.Recipe;
import java.util.List;

/**
 * Interface for a cookbook repository.
 *
 * <p>Manages recipes in a cookbook. This interface works as a single interface to support different
 * storage solutions.
 *
 * @see Recipe
 * @see RecipeRepository
 * @see InMemoryCookbookRepository
 */
public interface CookbookRepository {
  /**
   * Add a recipe to the cookbook.
   *
   * @param recipe The recipe to add
   */
  void addRecipe(Recipe recipe);

  /**
   * Remove a recipe from the cookbook.
   *
   * @param recipe The recipe to remove
   */
  void removeRecipe(Recipe recipe);

  /**
   * Get all recipes in the cookbook.
   *
   * @return A list of all recipes
   */
  List<Recipe> getAllRecipes();

  /**
   * Find recipes by name.
   *
   * @param name The name to search for
   * @return A list of matching recipes
   */
  List<Recipe> findRecipesByName(String name);
}
