package edu.ntnu.idi.idatt.model;

import java.util.List;
import java.util.Map;

/** A class representing a recipe. */
public class Recipe {
  String name;
  String description;
  String instructions;
  List<Map.Entry<Grocery, Double>> ingredients;

  /**
   * Constructor for a recipe.
   *
   * @param name The name of the recipe.
   * @param description A description of the recipe.
   * @param instructions The instructions for how to make the recipe.
   * @param ingredients A list of ingredients needed to make the recipe.
   */
  public Recipe(
      String name,
      String description,
      String instructions,
      List<Map.Entry<Grocery, Double>> ingredients) {
    this.name = name;
    this.description = description;
    this.instructions = instructions;
    this.ingredients = ingredients;
  }

  /**
   * Get the name of the recipe.
   *
   * @return The name of the recipe.
   */
  public String getName() {
    return name;
  }

  /**
   * Set the name of the recipe.
   *
   * @param name The name of the recipe.
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Get the description of the recipe.
   *
   * @return The description of the recipe.
   */
  public String getDescription() {
    return description;
  }

  /**
   * Set the description of the recipe.
   *
   * @param description The description of the recipe.
   */
  public void setDescription(String description) {
    this.description = description;
  }

  /**
   * Get the instructions for how to make the recipe.
   *
   * @return The instruction for how to make the recipe.
   */
  public String getInstructions() {
    return instructions;
  }

  /**
   * Set the instructions for how to make the recipe.
   *
   * @param instructions The instructions for how to make the recipe.
   */
  public void setInstructions(String instructions) {
    this.instructions = instructions;
  }

  /**
   * Get the ingredients needed to make the recipe.
   *
   * @return The ingredients needed to make the recipe.
   */
  public List<Map.Entry<Grocery, Double>> getIngredients() {
    return ingredients;
  }

  /**
   * Set the ingredients needed to make the recipe.
   *
   * @param ingredients The ingredients needed to make the recipe.
   */
  public void setIngredients(List<Map.Entry<Grocery, Double>> ingredients) {
    this.ingredients = ingredients;
  }
}
