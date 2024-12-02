package edu.ntnu.idi.idatt.view;

import edu.ntnu.idi.idatt.model.Grocery;
import edu.ntnu.idi.idatt.model.Recipe;
import edu.ntnu.idi.idatt.service.IngredientService;
import java.util.List;

/** Class representing the view for recipes. */
public class RecipeView {
  /**
   * Displays a recipe.
   *
   * @param recipe The recipe name to display
   */
  public static void displayRecipeName(Recipe recipe) {
    System.out.println("- " + recipe.getName());
  }

  /**
   * Displays a recipe in full.
   *
   * @param recipe The recipe to display
   */
  public static void displayRecipe(Recipe recipe) {
    System.out.println("Recipe: " + recipe.getName());
    System.out.println("Description: " + recipe.getDescription());
    System.out.println("Instructions: " + recipe.getInstructions());
    System.out.println("Ingredients:");
    recipe
        .getIngredients()
        .forEach(
            ingredient ->
                System.out.println(
                    ingredient.getName()
                        + " "
                        + ingredient.getAmount()
                        + " "
                        + ingredient.getMeasurementUnit()));
  }

  /**
   * Displays all recipes.
   *
   * @param recipes The list of recipes to display
   */
  public static void displayAllRecipes(List<Recipe> recipes) {
    if (recipes.isEmpty()) {
      System.out.println("No recipes found.");
    } else {
      recipes.forEach(RecipeView::displayRecipeName);
    }
  }

  /**
   * Displays whether a recipe can be made with the available ingredients.
   *
   * @param recipe The recipe to check
   * @param availableIngredients A map of available groceries and their quantities
   */
  public static void displayCanMakeRecipe(Recipe recipe, List<Grocery> availableIngredients) {
    if (IngredientService.hasAllIngredients(recipe, availableIngredients)) {
      System.out.println("You can make " + recipe.getName());
    } else {
      System.out.println("You can't make " + recipe.getName());
    }
  }

  /**
   * Displays a suggested recipe name based on available ingredients.
   *
   * @param suggestedRecipe The suggested recipe to display.
   */
  public static void displaySuggestedRecipe(Recipe suggestedRecipe) {
    if (suggestedRecipe == null) {
      System.out.println("No recipe can be made with the available ingredients.");
    } else {
      System.out.println("Suggested recipe: " + suggestedRecipe.getName());
    }
  }
}
