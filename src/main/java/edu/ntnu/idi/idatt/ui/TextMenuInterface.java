package edu.ntnu.idi.idatt.ui;

import edu.ntnu.idi.idatt.model.Grocery;
import edu.ntnu.idi.idatt.model.Recipe;
import edu.ntnu.idi.idatt.util.TextMenuInputValidator;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/** Text-based menu interface for the user. */
public class TextMenuInterface extends UserInterface {
  Scanner scanner = new Scanner(System.in);

  /** Starts the text-based menu interface. */
  public void start() {
    int choice = -1;
    while (choice != 0) {
      printMenu();
      choice = TextMenuInputValidator.readInt("Enter choice: ");
      switch (choice) {
        case 1 -> addGrocery();
        case 2 -> removeGrocery();
        case 3 -> listGroceries();
        case 4 -> listExpiredGroceries();
        case 5 -> calculateTotalValue();
        case 6 -> addRecipe();
        case 7 -> removeRecipe();
        case 8 -> listRecipes();
        case 9 -> saveRecipeToCookbook();
        case 10 -> checkRecipe();
        case 11 -> listRecipesInCookbook();
        case 0 -> System.out.println("Exiting...");
        default -> System.out.println("Invalid choice");
      }
    }
  }

  /** Prints the menu of choices for the user to perform. */
  private static void printMenu() {
    System.out.println("\nMenu:");
    System.out.println("1. Add grocery");
    System.out.println("2. Remove grocery");
    System.out.println("3. List groceries");
    System.out.println("4. List expired groceries");
    System.out.println("5. Calculate total value of groceries");
    System.out.println("6. Add recipe");
    System.out.println("7. Remove recipe");
    System.out.println("8. List recipes");
    System.out.println("9. Save recipe to cookbook");
    System.out.println("10. Check if recipe can be made");
    System.out.println("11. List recipes in cookbook");
    System.out.println("0. Exit");
  }

  /** Adds a grocery to storage. */
  private void addGrocery() {
    System.out.println("Adding grocery...");
    final String name = TextMenuInputValidator.readName();

    final double amount = TextMenuInputValidator.readPositiveDouble("Enter amount: ");
    final String unit = TextMenuInputValidator.readUnit();

    System.out.println("Enter expiration date: ");
    final LocalDate expirationDate = TextMenuInputValidator.readDate();

    final double price = TextMenuInputValidator.readPositiveDouble("Enter price per unit (NOK): ");

    System.out.println();

    try {
      storageController.addGrocery(name, amount, unit, expirationDate, price);
      System.out.println("Grocery added successfully.");
    } catch (Exception e) {
      System.out.println("Error adding grocery: " + e.getMessage());
    }
  }

  /** Removes a grocery from storage. */
  private void removeGrocery() {
    System.out.print("Enter name of grocery to remove: ");
    String name = scanner.nextLine();

    try {
      if (storageController.groceryInStorage(name)) {
        storageController.removeGrocery(name);
        System.out.println("Grocery removed successfully.");
      } else {
        System.out.println("Grocery not found. Please check the name and try again.");
      }
    } catch (Exception e) {
      System.out.println("Error removing grocery: " + e.getMessage());
    }
  }

  /** Lists groceries in storage. */
  private void listGroceries() {
    System.out.println("Listing groceries...");

    storageController.listAllGroceries();
  }

  /** Lists expired groceries in storage and the total value of these groceries. */
  private void listExpiredGroceries() {
    System.out.println("Listing expired groceries...");

    storageController.listExpiredGroceries();
    storageController.displayExpiredTotalValue();
  }

  /** Prints the total value of all groceries in storage. */
  private void calculateTotalValue() {
    storageController.displayTotalValue();
  }

  /** Adds a recipe to the cookbook. */
  private void addRecipe() {
    String recipeName = TextMenuInputValidator.readName();
    String description = TextMenuInputValidator.readText("description");
    String instructions = TextMenuInputValidator.readText("instructions");
    List<Map.Entry<Grocery, Double>> ingredients = TextMenuInputValidator.readIngredients();

    try {
      recipeController.addRecipe(recipeName, description, instructions, ingredients);
      System.out.println("Recipe added successfully.");
    } catch (Exception e) {
      System.out.println("Error adding recipe: " + e.getMessage());
    }
  }

  /** Removes a recipe from the cookbook. */
  private void removeRecipe() {
    System.out.print("Enter name of recipe to remove: ");
    String name = scanner.nextLine();

    try {
      List<Recipe> recipes = recipeController.getAllRecipes();
      for (Recipe recipe : recipes) {
        if (recipe.getName().equalsIgnoreCase(name)) {
          recipeController.removeRecipe(recipe);
          System.out.println("Recipe removed successfully.");
          return;
        }
      }
      System.out.println("Recipe not found. Please check the name and try again.");
    } catch (Exception e) {
      System.out.println("Error removing recipe: " + e.getMessage());
    }
  }

  /** Lists all recipes in the cookbook. */
  private void listRecipes() {
    System.out.println("Listing recipes...");

    recipeController.displayAllRecipes();
  }

  /** Checks if a recipe can be made with available ingredients. */
  private void checkRecipe() {
    System.out.print("Enter name of recipe to check: ");
    String name = scanner.nextLine();

    try {
      List<Recipe> recipes = recipeController.findRecipesByName(name);
      if (recipes.isEmpty()) {
        System.out.println("Recipe not found. Please check the name and try again.");
        return;
      }
      Recipe recipe = recipes.get(0);
      recipeController.canMakeRecipe(recipe, storageController.getAllGroceriesWithAmount());
      System.out.println("Recipe not found. Please check the name and try again.");
    } catch (Exception e) {
      System.out.println("Error checking recipe: " + e.getMessage());
    }
  }

  /** Saves a recipe to the cookbook. */
  private void saveRecipeToCookbook() {
    String recipeName = TextMenuInputValidator.readName();

    try {
      Recipe recipe = recipeController.findRecipesByName(recipeName).get(0);
      recipeController.saveRecipeToCookbook(recipe);
    } catch (IndexOutOfBoundsException e) {
      System.out.println("Recipe not found.");
    }
  }

  /** Lists all recipes in the cookbook. */
  private void listRecipesInCookbook() {
    System.out.println("Listing recipes in cookbook...");

    recipeController.displayCookbookRecipes();
  }
}
