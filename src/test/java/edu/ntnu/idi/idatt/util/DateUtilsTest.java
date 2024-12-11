package edu.ntnu.idi.idatt.util;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import org.junit.jupiter.api.Test;

/** Tests parsing and formatting dates using the DateUtils class. */
class DateUtilsTest {

  /** Tests that a valid date string is correctly parsed. */
  @Test
  void parseValidDateTest() {
    String dateStr = "25-12-2024";

    LocalDate date = LocalDate.parse(dateStr, DateUtils.FORMATTER);

    assertEquals(LocalDate.of(2024, 12, 25), date);
  }

  /** Tests that parsing an invalid date string throws an exception. */
  @Test
  void parseInvalidDateTest() {
    String invalidDateStr = "99-99-9999";

    assertThrows(
        DateTimeParseException.class, () -> LocalDate.parse(invalidDateStr, DateUtils.FORMATTER));
  }

  /** Tests that a LocalDate can be formatted correctly into a string. */
  @Test
  void formatDateTest() {
    LocalDate date = LocalDate.of(2024, 12, 25);

    String formatted = date.format(DateUtils.FORMATTER);

    assertEquals("25-12-2024", formatted);
  }
}
