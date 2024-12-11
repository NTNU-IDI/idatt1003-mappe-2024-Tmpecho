package edu.ntnu.idi.idatt.model;

import java.util.List;
import java.util.Objects;

/** A class representing a recipe. */
public class Recipe {
  String name;
  String description;
  String instructions;
  List<Grocery> ingredients;

  /**
   * Constructor for a recipe.
   *
   * @param name The name of the recipe.
   * @param description A description of the recipe.
   * @param instructions The instructions for how to make the recipe.
   * @param ingredients A list of ingredients needed to make the recipe.
   */
  public Recipe(String name, String description, String instructions, List<Grocery> ingredients) {
    this.name = name;
    this.description = description;
    this.instructions = instructions;
    this.ingredients = ingredients;
  }

  /**
   * Get the name of the recipe.
   *
   * @return The name of the recipe
   */
  public String getName() {
    return name;
  }

  /**
   * Set the name of the recipe.
   *
   * @param name The name of the recipe
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Get the description of the recipe.
   *
   * @return The description of the recipe
   */
  public String getDescription() {
    return description;
  }

  /**
   * Set the description of the recipe.
   *
   * @param description The description of the recipe
   */
  public void setDescription(String description) {
    this.description = description;
  }

  /**
   * Get the instructions for how to make the recipe.
   *
   * @return The instruction for how to make the recipe
   */
  public String getInstructions() {
    return instructions;
  }

  /**
   * Set the instructions for how to make the recipe.
   *
   * @param instructions The instructions for how to make the recipe
   */
  public void setInstructions(String instructions) {
    this.instructions = instructions;
  }

  /**
   * Get the ingredients needed to make the recipe.
   *
   * @return The ingredients needed to make the recipe
   */
  public List<Grocery> getIngredients() {
    return ingredients;
  }

  /**
   * Set the ingredients needed to make the recipe.
   *
   * @param ingredients The ingredients needed to make the recipe
   */
  public void setIngredients(List<Grocery> ingredients) {
    this.ingredients = ingredients;
  }

  /**
   * Checks if two {@code Recipe} objects are equal based on their name, description, and
   * ingredients.
   *
   * @param o The other object to compare
   * @return True if the recipes are equal, false otherwise
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Recipe recipe = (Recipe) o;
    return Objects.equals(name, recipe.name)
        && Objects.equals(description, recipe.description)
        && Objects.equals(instructions, recipe.instructions)
        && Objects.equals(ingredients, recipe.ingredients);
  }

  /**
   * Generates a hash code for the {@code Recipe} object based on its attributes.
   *
   * @return The hash code
   */
  @Override
  public int hashCode() {
    return Objects.hash(name, description, instructions, ingredients);
  }
}
