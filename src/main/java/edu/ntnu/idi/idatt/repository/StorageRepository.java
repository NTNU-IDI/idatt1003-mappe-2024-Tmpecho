package edu.ntnu.idi.idatt.repository;

import java.util.List;
import edu.ntnu.idi.idatt.model.Grocery;

public interface StorageRepository {
  void addGrocery(Grocery grocery);

  Grocery getGrocery(String name);

  void removeGrocery(String name);

  void removeGrocery(String name, double amount);

  List<Grocery> listAllGroceries();

  List<Grocery> listExpiredGroceries();

  double calculateTotalValue();
}
