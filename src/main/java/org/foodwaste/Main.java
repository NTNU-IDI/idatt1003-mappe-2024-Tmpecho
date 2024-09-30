package org.foodwaste;

import org.foodwaste.ui.UI;

public class Main {
  public static void main(String[] args) {
    System.out.println("Hello world!");

    UI ui = new UI();
    ui.init();
    ui.start();
  }
}
