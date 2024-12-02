package edu.ntnu.idi.idatt.util;

import edu.ntnu.idi.idatt.model.Grocery;
import edu.ntnu.idi.idatt.model.Recipe;
import java.util.Map;

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
    for (Map.Entry<Grocery, Double> ingredient : recipe.getIngredients()) {
      Grocery grocery = ingredient.getKey();
      Double requiredQuantity = ingredient.getValue();
      Double availableQuantity = availableIngredients.getOrDefault(grocery, 0.0);
      if (availableQuantity < requiredQuantity) {
        return false;
      }
    }
    return true;
  }
}
