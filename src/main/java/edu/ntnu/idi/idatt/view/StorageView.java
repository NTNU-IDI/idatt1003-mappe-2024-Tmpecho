package edu.ntnu.idi.idatt.view;

import edu.ntnu.idi.idatt.model.Grocery;

import java.util.List;

public class StorageView {
  public void displayStorage(List<Grocery> groceries) {
    groceries.forEach(System.out::println);
  }
}
