package edu.ntnu.idi.idatt.controller;

import edu.ntnu.idi.idatt.model.Grocery;
import edu.ntnu.idi.idatt.model.Recipe;
import edu.ntnu.idi.idatt.repository.CookbookRepository;
import edu.ntnu.idi.idatt.repository.RecipeRepository;
import edu.ntnu.idi.idatt.view.RecipeView;
import java.util.List;
import java.util.Map;

/** Controller for managing recipes. */
public class RecipeController {
  private final RecipeRepository recipeRepository;
  private final CookbookRepository cookbookRepository;

  /**
   * Constructor for the RecipeController class.
   *
   * @param recipeRepository repository for the recipe model.
   * @param cookbookRepository repository for the cookbook model.
   */
  public RecipeController(
      RecipeRepository recipeRepository, CookbookRepository cookbookRepository) {
    this.recipeRepository = recipeRepository;
    this.cookbookRepository = cookbookRepository;
  }

  /**
   * Method for adding a recipe to the recipe model.
   *
   * @param name The name of the recipe.
   * @param description A description of the recipe.
   * @param instructions The instructions for how to make the recipe.
   * @param ingredients A list of ingredients needed to make the recipe.
   */
  public void addRecipe(
      String name, String description, String instructions, List<Grocery> ingredients) {
    Recipe recipe = new Recipe(name, description, instructions, ingredients);
    recipeRepository.addRecipe(recipe);
  }

  /**
   * Method for removing a recipe from the recipe model.
   *
   * @param recipe The recipe to remove.
   */
  public void removeRecipe(Recipe recipe) {
    recipeRepository.removeRecipe(recipe);
    System.out.println("Recipe removed.");
  }

  /**
   * Method for saving a recipe to the cookbook.
   *
   * @param recipe The recipe to save.
   */
  public void saveRecipeToCookbook(Recipe recipe) {
    if (cookbookRepository.getAllRecipes().contains(recipe)) {
      System.out.println("Recipe is already in the cookbook.");
      return;
    }
    cookbookRepository.addRecipe(recipe);
    System.out.println("Recipe added to the cookbook.");
  }

  /**
   * Method for getting all recipes from the recipe model.
   *
   * @return A list of all recipes.
   */
  public List<Recipe> getAllRecipes() {
    return recipeRepository.getAllRecipes();
  }

  /**
   * Method for finding recipes by name.
   *
   * @param name The name to search for.
   * @return A list of matching recipes.
   */
  public List<Recipe> findRecipesByName(String name) {
    return recipeRepository.findRecipesByName(name);
  }

  /**
   * Method for checking if a recipe can be made with available ingredients.
   *
   * @param recipe The recipe to check.
   * @param availableIngredients A map of available ingredients.
   */
  public void canMakeRecipe(Recipe recipe, Map<Grocery, Double> availableIngredients) {
    RecipeView.displayCanMakeRecipe(recipe, availableIngredients);
  }

  /** Method for displaying all recipes. */
  public void displayAllRecipes() {
    List<Recipe> recipes = getAllRecipes();
    RecipeView.displayAllRecipes(recipes);
  }

  /** Method for displaying all recipes in the cookbook. */
  public void displayCookbookRecipes() {
    List<Recipe> recipes = cookbookRepository.getAllRecipes();
    RecipeView.displayAllRecipes(recipes);
  }
}
