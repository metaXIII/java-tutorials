package com.metaxiii.fr;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.Timestamp;
import org.joda.time.DateTime;
import org.junit.jupiter.api.Test;

class DateTimeAndTimestampConverterTest {

  @Test
  void givenDateTime_whenUsingConstructor_thenConvertToTimestamp() {
    final long currentTimeMillis = System.currentTimeMillis();
    final DateTime dateTime = new DateTime(currentTimeMillis);
    final Timestamp expectedTimestamp = new Timestamp(currentTimeMillis);
    final Timestamp convertedTimestamp = DateTimeAndTimestampConverter.convertToTimestampUsingConstructor(dateTime);
    assertEquals(expectedTimestamp, convertedTimestamp);
  }

  @Test
  void givenDateTime_whenUsingInstant_thenConvertToTimestamp() {
    final long currentTimeMillis = System.currentTimeMillis();
    final DateTime dateTime = new DateTime(currentTimeMillis);
    final Timestamp expectedTimestamp = new Timestamp(currentTimeMillis);
    final Timestamp convertedTimestamp = DateTimeAndTimestampConverter.convertToTimestampUsingInstant(dateTime);
    assertEquals(expectedTimestamp, convertedTimestamp);
  }

  @Test
  void givenDateTime_whenUsingLocalDateTime_thenConvertToTimestamp() {
    final long currentTimeMillis = System.currentTimeMillis();
    final DateTime dateTime = new DateTime(currentTimeMillis);
    final Timestamp expectedTimestamp = new Timestamp(currentTimeMillis);
    final Timestamp convertedTimestamp = DateTimeAndTimestampConverter.convertToTimestampUsingLocalDateTime(dateTime);
    assertEquals(expectedTimestamp, convertedTimestamp);
  }

  @Test
  void givenTimestamp_whenUsingConstructor_thenConvertToDateTime() {
    final long currentTimeMillis = System.currentTimeMillis();
    final Timestamp timestamp = new Timestamp(currentTimeMillis);
    final DateTime expectedDateTime = new DateTime(currentTimeMillis);
    final DateTime convertedDateTime = DateTimeAndTimestampConverter.convertToDateTimeUsingConstructor(timestamp);
    assertEquals(expectedDateTime, convertedDateTime);
  }

  @Test
  void givenTimestamp_whenUsingInstant_thenConvertToDateTime() {
    final long currentTimeMillis = System.currentTimeMillis();
    final Timestamp timestamp = new Timestamp(currentTimeMillis);
    final DateTime expectedDateTime = new DateTime(currentTimeMillis);
    final DateTime convertedDateTime = DateTimeAndTimestampConverter.convertToDateTimeUsingInstant(timestamp);
    assertEquals(expectedDateTime, convertedDateTime);
  }

  @Test
  void givenTimestamp_whenUsingLocalDateTime_thenConvertToDateTime() {
    final long currentTimeMillis = System.currentTimeMillis();
    final Timestamp timestamp = new Timestamp(currentTimeMillis);
    final DateTime expectedDateTime = new DateTime(currentTimeMillis);
    final DateTime convertedDateTime = DateTimeAndTimestampConverter.convertToDateTimeUsingLocalDateTime(timestamp);
    assertEquals(expectedDateTime, convertedDateTime);
  }
}
