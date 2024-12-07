package edu.ntnu.idi.idatt.service;

import edu.ntnu.idi.idatt.model.Grocery;
import edu.ntnu.idi.idatt.model.Recipe;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/** Service class for suggesting recipes based on available groceries. */
public class RecipeSuggestionService {
  /**
   * Suggest a recipe based on available groceries. Finds the recipe with the most matching
   * ingredients. If no recipe has any overlap, returns {@code null}.
   *
   * @param recipes The list of recipes to evaluate.
   * @param availableGroceries The groceries available in storage.
   * @return The suggested recipe, or {@code null} if no recipe can be made.
   */
  public Recipe suggestRecipe(List<Recipe> recipes, List<Grocery> availableGroceries) {
    Map<String, Double> groceryMap =
        availableGroceries.stream()
            .collect(Collectors.toMap(Grocery::getName, Grocery::getAmount, Double::sum));

    // Filter out recipes that have no matching ingredients with the available groceries
    List<Recipe> matchingRecipes =
        recipes.stream()
            .filter(
                recipe ->
                    recipe.getIngredients().stream()
                        .anyMatch(
                            ingredient ->
                                groceryMap.containsKey(ingredient.getName().toLowerCase())))
            .toList();

    if (matchingRecipes.isEmpty()) {
      return null;
    }

    // Find the recipe with the most matching ingredients
    return matchingRecipes.stream()
        .max(
            (recipe1, recipe2) -> {
              double recipe1Match = calculateMatchScore(recipe1, groceryMap);
              double recipe2Match = calculateMatchScore(recipe2, groceryMap);
              return Double.compare(recipe1Match, recipe2Match);
            })
        .orElse(null);
  }

  /**
   * Calculates the match score for a recipe based on the percentage of ingredients that are
   * available in sufficient quantity.
   *
   * @param recipe The recipe to evaluate.
   * @param availableGroceries Map of grocery names to available quantities.
   * @return The match score (0.0 to 1.0) for the recipe.
   */
  private double calculateMatchScore(Recipe recipe, Map<String, Double> availableGroceries) {
    long totalIngredients = recipe.getIngredients().size();
    long matchingIngredients =
        recipe.getIngredients().stream()
            .filter(
                ingredient -> {
                  double requiredAmount = ingredient.getAmount();
                  double availableAmount =
                      availableGroceries.getOrDefault(ingredient.getName().toLowerCase(), 0.0);
                  return availableAmount >= requiredAmount;
                })
            .count();
    return (double) matchingIngredients / totalIngredients;
  }
}
