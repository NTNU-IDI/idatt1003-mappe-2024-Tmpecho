package edu.ntnu.idi.idatt.util;

import java.time.format.DateTimeFormatter;

/**
 * Utility class for date operations.
 *
 * <p>Contains a {@link DateTimeFormatter} for formatting dates.
 */
public class DateUtils {
  public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy");
}
