package org.foodwaste;

import org.foodwaste.ui.CliInterface;
import org.foodwaste.ui.UserInterface;

/**
 * Main class that starts the application.
 */
public class Main {
  /**
   * Main entry point for the application that starts the user interface.
   *
   * @param args Command line arguments
   */
  public static void main(String[] args) {
    UserInterface userInterface = new CliInterface(); // eller 'new TextMenuInterface();' f.eks.
    userInterface.init();
    userInterface.start();
  }
}
