package edu.ntnu.idi.idatt.view;

import edu.ntnu.idi.idatt.model.Grocery;
import edu.ntnu.idi.idatt.model.Recipe;
import edu.ntnu.idi.idatt.util.IngredientChecker;

import java.util.List;
import java.util.Map;

/** Class representing the view for recipes. */
public class RecipeView {
  /**
   * Displays a recipe.
   *
   * @param recipe The recipe to display
   */
  public void displayRecipe(Recipe recipe) {
    System.out.println("Recipe: " + recipe.getName());
    System.out.println("Description: " + recipe.getDescription());
    System.out.println("Instructions: " + recipe.getInstructions());
    System.out.println("Ingredients:");
    recipe
        .getIngredients()
        .forEach(
            ingredient ->
                System.out.println(
                    "- " + ingredient.getKey().getName() + ": " + ingredient.getValue()));
  }

  /**
   * Displays all recipes.
   *
   * @param recipes The list of recipes to display
   */
  public void displayAllRecipes(List<Recipe> recipes) {
    if (recipes.isEmpty()) {
      System.out.println("No recipes found.");
    } else {
      recipes.forEach(this::displayRecipe);
    }
  }

  public void displayCanMakeRecipe(Recipe recipe, Map<Grocery, Double> availableIngredients) {
    if (IngredientChecker.hasAllIngredients(recipe, availableIngredients)) {
      System.out.println("You can make " + recipe.getName());
    } else {
      System.out.println("You can't make " + recipe.getName());
    }
  }
}
