package edu.ntnu.idi.idatt.util;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import org.junit.jupiter.api.Test;

/** Tests for the DateUtils class. Ensures proper date parsing and formatting. */
class DateUtilsTest {

  /** Tests parsing a valid date string using the DateUtils.FORMATTER. */
  @Test
  void parseValidDateTest() {
    String dateStr = "25-12-2024";
    LocalDate date = LocalDate.parse(dateStr, DateUtils.FORMATTER);
    assertEquals(LocalDate.of(2024, 12, 25), date);
  }

  /** Tests parsing an invalid date string and expects a DateTimeParseException. */
  @Test
  void parseInvalidDateTest() {
    String invalidDateStr = "99-99-9999";
    assertThrows(
        DateTimeParseException.class, () -> LocalDate.parse(invalidDateStr, DateUtils.FORMATTER));
  }

  /** Tests formatting a LocalDate into a string using the DateUtils.FORMATTER. */
  @Test
  void formatDateTest() {
    LocalDate date = LocalDate.of(2024, 12, 25);
    String formatted = date.format(DateUtils.FORMATTER);
    assertEquals("25-12-2024", formatted);
  }
}
