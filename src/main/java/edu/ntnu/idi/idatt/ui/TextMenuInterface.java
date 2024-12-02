package edu.ntnu.idi.idatt.ui;

import edu.ntnu.idi.idatt.model.Grocery;
import edu.ntnu.idi.idatt.model.MeasurementUnit;
import edu.ntnu.idi.idatt.model.Recipe;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.ArrayList;
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
      choice = readInt("Enter choice: ");
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
  private void printMenu() {
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

  /**
   * Reads a name from the user with a prompt.
   *
   * @return the name entered by the user
   */
  private String readName() {
    while (true) {
      System.out.print("Enter name: ");
      String name = scanner.nextLine();
      if (name == null || name.isEmpty()) {
        System.out.println("Name cannot be empty. Please enter a name.");
        continue;
      }
      return name;
    }
  }

  /**
   * Reads a text from the user with a prompt.
   *
   * @return the text entered by the user
   */
  private String readText(String prompt) {
    while (true) {
      System.out.print("Enter " + prompt + ": ");
      String text = scanner.nextLine();
      if (text == null || text.isEmpty()) {
        System.out.println("Text cannot be empty. Please enter a text.");
        continue;
      }
      return text;
    }
  }

  /**
   * Reads ingredients for a recipe from the user.
   *
   * @return a list of ingredients with their amounts
   */
  private List<Map.Entry<Grocery, Double>> readIngredients() {
    List<Map.Entry<Grocery, Double>> ingredients = new ArrayList<>();

    System.out.println("Enter ingredients for the recipe (type 'done' when finished):");

    while (true) {
      String name = readName();
      if (name.equalsIgnoreCase("done")) {
        break;
      }

      if (ingredients.stream().anyMatch(entry -> entry.getKey().getName().equalsIgnoreCase(name))) {
        System.out.println("Ingredient already exists. Please enter a different ingredient.");
        continue;
      }

      double amount = readPositiveDouble("Enter amount for " + name + ": ");
      String unit = readUnit();

      Grocery ingredient = new Grocery(name, amount, MeasurementUnit.fromString(unit), null, null);

      try {
        ingredients.add(Map.entry(ingredient, amount));
        System.out.println("Ingredient '" + name + "' added successfully.");
      } catch (Exception e) {
        System.out.println("Error adding ingredient: " + e.getMessage());
      }
    }

    return ingredients;
  }

  /**
   * Reads an integer from the user with a prompt.
   *
   * @param prompt the prompt to display to the user
   * @return the integer entered by the user
   */
  private int readInt(String prompt) {
    while (true) {
      System.out.print(prompt);
      try {
        return Integer.parseInt(scanner.nextLine());
      } catch (NumberFormatException e) {
        System.out.println("Invalid input. Please enter an integer.");
      }
    }
  }

  /**
   * Reads a positive non-zero double from the user with a prompt.
   *
   * @param prompt the prompt to display to the user
   * @return the double entered by the user
   */
  private double readPositiveDouble(String prompt) {
    while (true) {
      System.out.print(prompt);
      try {
        double value = Double.parseDouble(scanner.nextLine());
        if (value == 0) {
          System.out.println("Value cannot be zero. Please enter a positive number.");
          continue;
        }
        if (value < 0) {
          System.out.println("Value cannot be negative. Please enter a positive number.");
          continue;
        }
        return value;
      } catch (NumberFormatException e) {
        System.out.println("Invalid input. Please enter a number.");
      }
    }
  }

  /**
   * Reads a valid measurement unit from the user.
   *
   * @return the unit entered by the user as a string
   */
  private String readUnit() {
    while (true) {
      System.out.print("Enter unit: ");
      String unit = scanner.nextLine();
      try {
        MeasurementUnit.fromString(unit);
        return unit;
      } catch (IllegalArgumentException e) {
        System.out.println(
            "Invalid unit. Please enter a valid measurement unit (e.g., liter, kg, pcs).");
      }
    }
  }

  /**
   * Reads a valid date from the user.
   *
   * @return the date entered by the user
   */
  private LocalDate readDate() {
    while (true) {
      try {
        int year = readInt("Year: ");
        int month = readInt("Month: ");
        int day = readInt("Day: ");
        return LocalDate.of(year, month, day);
      } catch (DateTimeException e) {
        System.out.println("Invalid date. Please enter a valid date.");
      }
    }
  }

  /** Adds a grocery to storage. */
  private void addGrocery() {
    System.out.println("Adding grocery...");
    final String name = readName();

    final double amount = readPositiveDouble("Enter amount: ");
    final String unit = readUnit();

    System.out.println("Enter expiration date: ");
    final LocalDate expirationDate = readDate();

    final double price = readPositiveDouble("Enter price per unit (NOK): ");

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
    String recipeName = readName();
    String description = readText("description");
    String instructions = readText("instructions");
    List<Map.Entry<Grocery, Double>> ingredients = readIngredients();

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
      Recipe recipe = recipeController.findRecipesByName(name).get(0);
      recipeController.canMakeRecipe(recipe, storageController.getAllGroceriesWithAmount());
      System.out.println("Recipe not found. Please check the name and try again.");
    } catch (Exception e) {
      System.out.println("Error checking recipe: " + e.getMessage());
    }
  }

  /** Saves a recipe to the cookbook. */
  private void saveRecipeToCookbook() {
    String recipeName = readName();

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
