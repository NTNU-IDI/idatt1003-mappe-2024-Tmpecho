package edu.ntnu.idi.idatt.service;

import edu.ntnu.idi.idatt.model.Grocery;
import edu.ntnu.idi.idatt.model.Recipe;
import java.util.List;

/** Service class for checking if a recipe can be made with the available ingredients. */
public class IngredientService {
  /**
   * Check if the user has enough ingredients for a recipe.
   *
   * @param recipe The recipe to check.
   * @param availableIngredients A list of available groceries.
   * @return {@code true} if all ingredients are available in required quantities, {@code false}
   *     otherwise.
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
