package edu.ntnu.idi.idatt.repository;

import edu.ntnu.idi.idatt.model.Recipe;
import java.util.List;

/** Interface for a recipe repository. */
public interface RecipeRepository {
  /**
   * Add a recipe to the repository.
   *
   * @param recipe The recipe to add
   */
  void addRecipe(Recipe recipe);

  /**
   * Remove a recipe from the repository.
   *
   * @param recipe The recipe to remove
   */
  void removeRecipe(Recipe recipe);

  /**
   * Get all recipes in the repository.
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
