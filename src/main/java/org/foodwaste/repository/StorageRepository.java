package org.foodwaste.repository;

import java.util.List;
import org.foodwaste.model.Grocery;

public interface StorageRepository {
  void addGrocery(Grocery grocery);

  Grocery getGrocery(String name);

  void removeGrocery(String name, float amount);

  List<Grocery> listAllGroceries();

  List<Grocery> listExpiredGroceries();

  float calculateTotalValue();
}
