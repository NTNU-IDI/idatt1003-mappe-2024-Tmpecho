package edu.ntnu.idi.idatt.model;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Represents a unit of measurement for groceries.
 *
 * <p>Each unit has a standardized name (e.g. LITER) and a set of aliases (e.g. liter, liters). The
 * aliases are used to parse user input and convert it to the corresponding enum value.
 *
 * <p>The measurement units are:
 *
 * <ul>
 *   <li>{@code LITER}
 *   <li>{@code KILOGRAM}
 *   <li>{@code PCS} (pieces)
 * </ul>
 */
public enum MeasurementUnit {
  LITER("l", "liter", "liters"),
  KILOGRAM("kg", "kilogram", "kilograms", "kilos"),
  PCS("pcs", "pieces", "piece", "stk");

  private static final Map<String, MeasurementUnit> lookupMap = new HashMap<>();

  static {
    // Populate a lookup map with aliases and enum values.
    Arrays.stream(MeasurementUnit.values())
        .forEach(
            unit -> {
              Arrays.stream(unit.aliases)
                  .forEach(alias -> lookupMap.put(alias.toLowerCase(), unit));
              lookupMap.put(unit.name().toLowerCase(), unit);
            });
  }

  private final String[] aliases;

  /**
   * Constructor for {@code MeasurementUnit} with aliases.
   *
   * @param aliases Alternate names for the unit
   */
  MeasurementUnit(String... aliases) {
    this.aliases = aliases;
  }

  /**
   * Finds a {@code MeasurementUnit} by its alias or enum name.
   *
   * @param input The input string
   * @return The corresponding MeasurementUnit
   * @throws IllegalArgumentException if the input doesn't match any unit
   */
  public static MeasurementUnit fromString(String input) {
    MeasurementUnit unit = lookupMap.get(input.toLowerCase());
    if (unit == null) {
      throw new IllegalArgumentException(
          "Invalid measurement unit: " + input + ". Valid options are: " + lookupMap.keySet());
    }
    return unit;
  }
}
