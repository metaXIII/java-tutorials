package com.metaxiii.fr;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.junit.jupiter.api.Test;

class MaskingTest {

  final String email = "testemailaddress@example.com";
  final String expectedMaskedEmail = "te**************@example.com";
  final String phoneNumber = "+1234567890";
  final String expectedMaskedPhoneNumber = "+******7890";

  @Test
  void givenEmailAddress_whenUsingRegex_thenMaskEmail() {
    final var atIndex = email.indexOf('@');
    final var regex = "(.{2})(.*)(@.*)";
    final var repeatedAsterisks = "*".repeat(atIndex - 2);
    final var maskedEmail = email.replaceAll(regex, "$1" + repeatedAsterisks + "$3");

    assertEquals(expectedMaskedEmail, maskedEmail);
  }

  @Test
  void givenEmailAddress_whenUsingStringManipulation_thenMaskEmail() {
    final var atIndex = email.indexOf('@');
    final var repeatedString = IntStream.range(0, atIndex - 2).mapToObj(i -> "*").collect(Collectors.joining());
    final var maskedPart = email.substring(0, atIndex - repeatedString.length()) + repeatedString;
    final var maskedEmail = maskedPart + email.substring(atIndex);

    assertEquals(expectedMaskedEmail, maskedEmail);
  }

  @Test
  void givenPhoneNumber_whenUsingRegex_thenMaskPhone() {
    final var lastDigitsIndex = phoneNumber.length() - 5;
    final var regex = "(\\+)(\\d+)(\\d{4})";
    final var repeatedAsterisks = "*".repeat(Math.max(0, lastDigitsIndex));
    final var maskedPhoneNumber = phoneNumber.replaceAll(regex, "$1" + repeatedAsterisks + "$3");

    assertEquals(expectedMaskedPhoneNumber, maskedPhoneNumber);
  }

  @Test
  void givenPhoneNumber_whenUsingStringManipulation_thenMaskPhone() {
    final var maskedPhoneNumber = phoneNumber.replaceAll("\\d(?=\\d{4})", "*");
    assertEquals(expectedMaskedPhoneNumber, maskedPhoneNumber);
  }
}
