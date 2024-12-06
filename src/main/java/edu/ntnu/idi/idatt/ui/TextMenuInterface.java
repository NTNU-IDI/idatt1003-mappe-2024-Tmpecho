package edu.ntnu.idi.idatt.ui;

import edu.ntnu.idi.idatt.model.Grocery;
import edu.ntnu.idi.idatt.model.Recipe;
import edu.ntnu.idi.idatt.util.TextMenuInputValidator;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

/** Text-based menu interface for the user. */
public class TextMenuInterface extends UserInterface {
  Scanner scanner = new Scanner(System.in);

  /** Prints the menu of choices for the user to perform. */
  private static void printMenu() {
    System.out.println("\nMenu:");
    System.out.println("1. Display storage details");
    System.out.println("2. Add grocery");
    System.out.println("3. Remove grocery");
    System.out.println("4. List groceries");
    System.out.println("5. List expired groceries");
    System.out.println("6. Calculate total value of groceries");
    System.out.println("7. Add recipe");
    System.out.println("8. Remove recipe");
    System.out.println("9. List recipes");
    System.out.println("10. Save recipe to cookbook");
    System.out.println("11. Check if recipe can be made");
    System.out.println("12. List recipes in cookbook");
    System.out.println("13. Display recipe");
    System.out.println("14. Suggest recipe");
    System.out.println("0. Exit");
  }

  /** Starts the text-based menu interface. */
  public void start() {
    int choice = -1;
    while (choice != 0) {
      printMenu();
      choice = TextMenuInputValidator.readInt("Enter choice: ");
      switch (choice) {
        case 1 -> displayStorageDetails();
        case 2 -> addGrocery();
        case 3 -> removeGrocery();
        case 4 -> listGroceries();
        case 5 -> listExpiredGroceries();
        case 6 -> calculateTotalValue();
        case 7 -> addRecipe();
        case 8 -> removeRecipe();
        case 9 -> listRecipes();
        case 10 -> saveRecipeToCookbook();
        case 11 -> checkRecipe();
        case 12 -> listRecipesInCookbook();
        case 13 -> displayRecipe();
        case 14 -> suggestRecipe();
        case 0 -> System.out.println("Exiting...");
        default -> System.out.println("Invalid choice");
      }
      if (choice != 0) {
        pressEnterToContinue();
      }
    }
  }

  /** Waits for the user to press {@code enter} before continuing. */
  private void pressEnterToContinue() {
    System.out.print("\nPress enter to continue...");
    scanner.nextLine();
  }

  /** Displays the storage details. */
  private void displayStorageDetails() {
    storageController.displayStorageDetails(fridge);
  }

  /** Adds a grocery to storage. */
  private void addGrocery() {
    System.out.println("Adding grocery...");
    final String name = TextMenuInputValidator.readName();
    final String unit = TextMenuInputValidator.readUnit();
    final double amount = TextMenuInputValidator.readPositiveDouble("Enter amount: ");
    final LocalDate expirationDate = TextMenuInputValidator.readDate();
    final double price = TextMenuInputValidator.readPositiveDouble("Enter price per unit (NOK): ");

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

    boolean success = storageController.listAllGroceries();
    if (!success) {
      System.out.println("No groceries found.");
    }
  }

  /** Lists expired groceries in storage and the total value of these groceries. */
  private void listExpiredGroceries() {
    System.out.println("Listing expired groceries...");

    boolean success = storageController.listExpiredGroceries();
    if (!success) {
      System.out.println("No expired groceries found.");
    }
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
    List<Grocery> ingredients = TextMenuInputValidator.readIngredients();

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
      recipeController.canMakeRecipe(recipe, storageController.getAllGroceries());
    } catch (Exception e) {
      System.out.println("Error checking recipe: " + e.getMessage());
    }
  }

  /** Saves a recipe to the cookbook. */
  private void saveRecipeToCookbook() {
    String recipeName = TextMenuInputValidator.readName();

    try {
      Recipe recipe = recipeController.findRecipesByName(recipeName).get(0);
      boolean success = recipeController.saveRecipeToCookbook(recipe);
      if (success) {
        System.out.println("Recipe saved to cookbook successfully.");
      } else {
        System.out.println("Recipe already exists in cookbook.");
      }
    } catch (IndexOutOfBoundsException e) {
      System.out.println("Recipe not found.");
    }
  }

  /** Lists all recipes in the cookbook. */
  private void listRecipesInCookbook() {
    System.out.println("Listing recipes in cookbook...");

    recipeController.displayCookbookRecipes();
  }

  /** Displays a full recipe. */
  private void displayRecipe() {
    System.out.print("Enter name of recipe to display: ");
    String name = scanner.nextLine();

    try {
      List<Recipe> recipes = recipeController.findRecipesByName(name);
      if (recipes.isEmpty()) {
        System.out.println("Recipe not found. Please check the name and try again.");
        return;
      }
      Recipe recipe = recipes.get(0);
      recipeController.displayRecipe(recipe);
    } catch (Exception e) {
      System.out.println("Error displaying recipe: " + e.getMessage());
    }
  }

  private void suggestRecipe() {
    System.out.println("Suggesting recipe...");

    Recipe recipe = recipeController.suggestRecipe(storageController.getAllGroceries());

    recipeController.displaySuggestedRecipe(recipe);

    if (recipe != null) {
      System.out.println("Do you wish to display the suggested recipe? (y/n)");

      String choice = scanner.nextLine();
      if (choice.equalsIgnoreCase("y")) {
        recipeController.displayRecipe(recipe);
      }
    }
  }
}
