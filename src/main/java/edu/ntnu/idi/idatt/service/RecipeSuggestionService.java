package edu.ntnu.idi.idatt.service;

import edu.ntnu.idi.idatt.model.Grocery;
import edu.ntnu.idi.idatt.model.Recipe;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/** Service class for suggesting recipes based on available groceries. */
public class RecipeSuggestionService {

  /**
   * Suggest a recipe based on available groceries. Finds the recipe that matches the most
   * ingredients in storage.
   *
   * <p>The method returns the recipe that can be made with the most available ingredients by
   * comparing the ingredients in the recipe with the available groceries. If no recipe can be made,
   * the method returns {@code null}.
   *
   * @param recipes The list of recipes to evaluate.
   * @param availableGroceries The groceries available in storage.
   * @return The suggested recipe, or {@code null} if no recipe can be made.
   */
  public Recipe suggestRecipe(List<Recipe> recipes, List<Grocery> availableGroceries) {
    Map<String, Double> groceryMap =
        availableGroceries.stream()
            .collect(Collectors.toMap(Grocery::getName, Grocery::getAmount, Double::sum));

    return recipes.stream()
        .max(
            (recipe1, recipe2) -> {
              int recipe1Match = countMatchingIngredients(recipe1, groceryMap);
              int recipe2Match = countMatchingIngredients(recipe2, groceryMap);
              return Integer.compare(recipe1Match, recipe2Match);
            })
        .orElse(null);
  }

  /**
   * Count how many ingredients in the recipe are available in sufficient quantity.
   *
   * @param recipe The recipe to evaluate.
   * @param availableGroceries Map of grocery names to available quantities.
   * @return The count of matching ingredients.
   */
  private int countMatchingIngredients(Recipe recipe, Map<String, Double> availableGroceries) {
    return (int)
        recipe.getIngredients().stream()
            .filter(
                ingredient -> {
                  double requiredAmount = ingredient.getAmount();
                  double availableAmount =
                      availableGroceries.getOrDefault(ingredient.getName().toLowerCase(), 0.0);
                  return availableAmount >= requiredAmount;
                })
            .count();
  }
}
