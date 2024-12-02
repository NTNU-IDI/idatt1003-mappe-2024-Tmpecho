package edu.ntnu.idi.idatt.util;

import edu.ntnu.idi.idatt.model.Grocery;
import edu.ntnu.idi.idatt.model.Recipe;
import java.util.Map;

/** Utility class for checking if a user has enough ingredients for a recipe. */
public class IngredientChecker {
  /**
   * Check if the user has enough ingredients for a recipe.
   *
   * @param recipe The recipe to check.
   * @param availableIngredients A map of available groceries and their quantities.
   * @return True if all ingredients are available in required quantities, false otherwise.
   */
  public static boolean hasAllIngredients(
      Recipe recipe, Map<Grocery, Double> availableIngredients) {
    for (Grocery ingredient : recipe.getIngredients()) {
      Double requiredQuantity = ingredient.getAmount();
      Double availableQuantity = availableIngredients.getOrDefault(ingredient, 0.0);
      if (availableQuantity < requiredQuantity) {
        return false;
      }
    }
    return true;
  }
}
