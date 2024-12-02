package edu.ntnu.idi.idatt.util;

import edu.ntnu.idi.idatt.model.Grocery;
import edu.ntnu.idi.idatt.model.MeasurementUnit;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/** Class for validating user input in a text-based menu. */
public class TextMenuInputValidator {
  static Scanner scanner = new Scanner(System.in);

  /**
   * Reads a name from the user with a prompt.
   *
   * @return the name entered by the user
   */
  public static String readName() {
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
  public static String readText(String prompt) {
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
  public static List<Grocery> readIngredients() {
    List<Grocery> ingredients = new ArrayList<>();

    System.out.println("Enter ingredients for the recipe (type 'done' when finished):");

    while (true) {
      String name = readName();
      if (name.equalsIgnoreCase("done")) {
        break;
      }

      if (ingredients.stream().anyMatch(entry -> entry.getName().equalsIgnoreCase(name))) {
        System.out.println("Ingredient already exists. Please enter a different ingredient.");
        continue;
      }

      double amount = readPositiveDouble("Enter amount for " + name + ": ");
      String unit = readUnit();

      Grocery ingredient = new Grocery(name, amount, MeasurementUnit.fromString(unit), null, null);

      try {
        ingredients.add(ingredient);
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
  public static int readInt(String prompt) {
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
  public static double readPositiveDouble(String prompt) {
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
  public static String readUnit() {
    while (true) {
      System.out.print("Enter unit: ");
      String unit = scanner.nextLine();
      try {
        MeasurementUnit.fromString(unit);
        return unit;
      } catch (IllegalArgumentException e) {
        System.out.println(e.getMessage());
      }
    }
  }

  /**
   * Reads a valid date from the user.
   *
   * @return the date entered by the user
   */
  public static LocalDate readDate() {
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
}
