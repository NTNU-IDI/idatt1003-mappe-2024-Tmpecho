package org.foodwaste.model;

import java.util.List;
import java.util.Map;

public class Recipe {
  String name;
  String description;
  String instructions;
  List<Map.Entry<Grocery, Integer>> ingredients;

  public Recipe(
      String name,
      String description,
      String instructions,
      List<Map.Entry<Grocery, Integer>> ingredients) {
    this.name = name;
    this.description = description;
    this.instructions = instructions;
    this.ingredients = ingredients;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getInstructions() {
    return instructions;
  }

  public void setInstructions(String instructions) {
    this.instructions = instructions;
  }

  public List<Map.Entry<Grocery, Integer>> getIngredients() {
    return ingredients;
  }

  public void setIngredients(List<Map.Entry<Grocery, Integer>> ingredients) {
    this.ingredients = ingredients;
  }
}
