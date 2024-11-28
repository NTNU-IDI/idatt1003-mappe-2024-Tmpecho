package edu.ntnu.idi.idatt.model;

import java.util.ArrayList;
import java.util.List;

/** A class representing a collection of recipes. */
public class Cookbook {
  private final List<Recipe> recipes;

  public Cookbook() {
    this.recipes = new ArrayList<>();
  }

  /**
   * Add a recipe to the cookbook.
   *
   * @param recipe The recipe to add.
   */
  public void addRecipe(Recipe recipe) {
    recipes.add(recipe);
  }

  /**
   * Remove a recipe from the cookbook.
   *
   * @param recipe The recipe to remove.
   */
  public void removeRecipe(Recipe recipe) {
    recipes.remove(recipe);
  }

  /**
   * Get all recipes in the cookbook.
   *
   * @return A list of all recipes.
   */
  public List<Recipe> getRecipes() {
    return new ArrayList<>(recipes);
  }

  /**
   * Find recipes based on name.
   *
   * @param name The name of the recipe to search for.
   * @return A list of recipes matching the name.
   */
  public List<Recipe> findRecipesByName(String name) {
    List<Recipe> result = new ArrayList<>();
    for (Recipe recipe : recipes) {
      if (recipe.getName().equalsIgnoreCase(name)) {
        result.add(recipe);
      }
    }
    return result;
  }
}
