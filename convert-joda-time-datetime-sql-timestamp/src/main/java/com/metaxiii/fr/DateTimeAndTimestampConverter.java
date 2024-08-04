package com.metaxiii.fr;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import org.joda.time.DateTime;

public class DateTimeAndTimestampConverter {

  private DateTimeAndTimestampConverter() {
    throw new IllegalStateException("Utility class");
  }

  public static DateTime convertToDateTimeUsingConstructor(final Timestamp timestamp) {
    return new DateTime(timestamp.getTime());
  }

  public static DateTime convertToDateTimeUsingInstant(final Timestamp timestamp) {
    final Instant instant = timestamp.toInstant();
    return new DateTime(instant.toEpochMilli());
  }

  public static DateTime convertToDateTimeUsingLocalDateTime(final Timestamp timestamp) {
    final LocalDateTime localDateTime = timestamp.toLocalDateTime();
    return new DateTime(localDateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
  }

  public static Timestamp convertToTimestampUsingConstructor(final DateTime dateTime) {
    return new Timestamp(dateTime.getMillis());
  }

  public static Timestamp convertToTimestampUsingInstant(final DateTime dateTime) {
    final Instant instant = Instant.ofEpochMilli(dateTime.getMillis());
    return Timestamp.from(instant);
  }

  public static Timestamp convertToTimestampUsingLocalDateTime(final DateTime dateTime) {
    final Instant instant = Instant.ofEpochMilli(dateTime.getMillis());
    final LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
    return Timestamp.valueOf(localDateTime);
  }
}
