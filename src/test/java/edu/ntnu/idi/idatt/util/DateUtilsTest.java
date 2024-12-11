package edu.ntnu.idi.idatt.util;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import org.junit.jupiter.api.Test;

class DateUtilsTest {

  @Test
  void parseValidDateTest() {
    String dateStr = "25-12-2024";

    LocalDate date = LocalDate.parse(dateStr, DateUtils.FORMATTER);

    assertEquals(LocalDate.of(2024, 12, 25), date);
  }

  @Test
  void parseInvalidDateTest() {
    String invalidDateStr = "99-99-9999";

    assertThrows(
        DateTimeParseException.class, () -> LocalDate.parse(invalidDateStr, DateUtils.FORMATTER));
  }

  @Test
  void formatDateTest() {
    LocalDate date = LocalDate.of(2024, 12, 25);

    String formatted = date.format(DateUtils.FORMATTER);

    assertEquals("25-12-2024", formatted);
  }
}
