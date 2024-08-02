package com.metaxiii.fr;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.util.Locale;
import org.joda.time.format.DateTimeFormat;
import org.junit.jupiter.api.Test;

class AppTest {

  @Test
  void givenMonthName_whenUsingChronoFieldEnum_thenReturnNumber() {
    final String givenMonthName = "Sep";
    final int expectedMonthNumber = 9;
    final int monthNumber = DateTimeFormatter
      .ofPattern("MMM")
      .withLocale(Locale.ENGLISH)
      .parse(givenMonthName)
      .get(ChronoField.MONTH_OF_YEAR);
    assertEquals(expectedMonthNumber, monthNumber);
  }

  @Test
  void givenMonthName_whenUsingJodaTime_thenReturnNumber() {
    final String givenMonthName = "April";
    final int expectedMonthNumber = 4;
    final int monthNumber = DateTimeFormat
      .forPattern("MMM")
      .withLocale(Locale.ENGLISH)
      .parseDateTime(givenMonthName)
      .getMonthOfYear();
    assertEquals(expectedMonthNumber, monthNumber);
  }

  @Test
  void givenMonthName_whenUsingMonthEnum_thenReturnNumber() {
    final String givenMonthName = "October";
    final int expectedMonthNumber = 10;
    final int monthNumber = Month.valueOf(givenMonthName.toUpperCase()).getValue();
    assertEquals(expectedMonthNumber, monthNumber);
  }
}
