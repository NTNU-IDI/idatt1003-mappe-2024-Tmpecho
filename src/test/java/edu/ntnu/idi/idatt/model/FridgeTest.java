package edu.ntnu.idi.idatt.model;

/** Test class for the Fridge class. */
class FridgeTest {
  Fridge fridge;

  /** Sets up a new fridge object before each test. */
  @org.junit.jupiter.api.BeforeEach
  void setUp() {
    fridge = new Fridge("Fridge", 100);
  }
}
