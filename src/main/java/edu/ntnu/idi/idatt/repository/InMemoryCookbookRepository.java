package edu.ntnu.idi.idatt.repository;

import edu.ntnu.idi.idatt.model.Recipe;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/** An in-memory implementation using standard List of the CookbookRepository. */
public class InMemoryCookbookRepository implements CookbookRepository {
  private final List<Recipe> recipes = new ArrayList<>();

  /**
   * Add a recipe to the list.
   *
   * @param recipe The recipe to add.
   */
  @Override
  public void addRecipe(Recipe recipe) {
    if (recipes.stream().anyMatch(r -> r.getName().equalsIgnoreCase(recipe.getName()))) {
      throw new IllegalArgumentException("Recipe with this name already exists in the cookbook.");
    }
    recipes.add(recipe);
  }

  /**
   * Remove a recipe from the list.
   *
   * @param recipe The recipe to remove.
   */
  @Override
  public void removeRecipe(Recipe recipe) {
    recipes.remove(recipe);
  }

  /**
   * Get all recipes in the list.
   *
   * @return A list of all recipes.
   */
  @Override
  public List<Recipe> getAllRecipes() {
    return new ArrayList<>(recipes);
  }

  /**
   * Find recipes by name.
   *
   * @param name The name to search for.
   * @return A list of matching recipes.
   */
  @Override
  public List<Recipe> findRecipesByName(String name) {
    return recipes.stream()
        .filter(recipe -> recipe.getName().equalsIgnoreCase(name))
        .collect(Collectors.toList());
  }
}
