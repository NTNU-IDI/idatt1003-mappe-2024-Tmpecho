package edu.ntnu.idi.idatt.util;

import edu.ntnu.idi.idatt.model.Grocery;
import edu.ntnu.idi.idatt.model.Recipe;
import java.util.List;

/**
 * Utility class for checking if a user has enough ingredients for a recipe.
 *
 * <p>Contains a method for checking if the user has enough ingredients for a recipe.
 *
 * @see Recipe
 * @see Grocery
 */
public class IngredientChecker {
  /**
   * Check if the user has enough ingredients for a recipe.
   *
   * @param recipe The recipe to check
   * @param availableIngredients A list of available groceries
   * @return True if all ingredients are available in required quantities, false otherwise
   */
  public static boolean hasAllIngredients(Recipe recipe, List<Grocery> availableIngredients) {
    for (Grocery ingredient : recipe.getIngredients()) {
      Double requiredQuantity = ingredient.getAmount();

      double totalAvailableQuantity =
          availableIngredients.stream()
              .filter(grocery -> grocery.getName().equalsIgnoreCase(ingredient.getName()))
              .mapToDouble(Grocery::getAmount)
              .sum();

      if (totalAvailableQuantity < requiredQuantity) {
        return false;
      }
    }
    return true;
  }
}
