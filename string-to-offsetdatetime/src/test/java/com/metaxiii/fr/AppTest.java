package com.metaxiii.fr;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import org.junit.jupiter.api.Test;

class AppTest {

  private static final String DATE_TIME_STRING = "2024-04-11T10:15:30+01:00";

  @Test
  void givenDateTimeStringAndFormatter_whenUsingDateTimeFormatter_thenConvertToOffsetDateTime() {
    final String customDateTimeString = "11-04-2024 10:15:30 +0100";
    final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss Z");
    final OffsetDateTime offsetDateTime = OffsetDateTime.parse(customDateTimeString, formatter);
    final OffsetDateTime expected = OffsetDateTime.of(2024, 4, 11, 10, 15, 30, 0, ZoneOffset.ofHours(1));
    assertEquals(expected, offsetDateTime);
  }

  @Test
  void givenDateTimeString_whenUsingOffsetDateTimeParse_thenConvertToOffsetDateTime() {
    final OffsetDateTime offsetDateTime = OffsetDateTime.parse(DATE_TIME_STRING);
    final OffsetDateTime expected = OffsetDateTime.of(2024, 4, 11, 10, 15, 30, 0, ZoneOffset.ofHours(1));
    assertEquals(expected, offsetDateTime);
  }
}
