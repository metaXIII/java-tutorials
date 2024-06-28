package com.metaxiii.fr;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.regex.Pattern;
import java.util.stream.Collectors;
import org.apache.commons.lang3.RegExUtils;
import org.junit.jupiter.api.Test;

class AppTest {

  private static final String INPUT_STRING = "M3a9n2y n8u6m7b5e4r0s1";
  private static final String EXPECTED_STRING = "Many numbers";
  private static final String DIGIT_REGEX = "\\d";

  @Test
  void whenUsingApacheCommonsLang_thenGetExpectedResult() {
    final var updatedString = RegExUtils.replacePattern(INPUT_STRING, DIGIT_REGEX, "");
    assertEquals(EXPECTED_STRING, updatedString);
  }

  @Test
  void whenUsingCharacterStream_thenGetExpectedResult() {
    final var updatedString = INPUT_STRING
      .chars()
      .filter(c -> !Character.isDigit(c))
      .mapToObj(c -> (char) c)
      .map(String::valueOf)
      .collect(Collectors.joining());
    assertEquals(EXPECTED_STRING, updatedString);
  }

  @Test
  void whenUsingForLoop_thenGetExpectedResult() {
    final var sb = new StringBuilder();
    for (final var ch : INPUT_STRING.toCharArray()) {
      if (!Character.isDigit(ch)) {
        sb.append(ch);
      }
    }
    assertEquals(EXPECTED_STRING, sb.toString());
  }

  @Test
  void whenUsingPatternAndMatcher_thenGetExpectedResult() {
    final var pattern = Pattern.compile(DIGIT_REGEX);
    final var matcher = pattern.matcher(INPUT_STRING);
    final var updatedString = matcher.replaceAll("");
    assertEquals(EXPECTED_STRING, updatedString);
  }

  @Test
  void whenUsingReplaceAll_thenGetExpectedResult() {
    final var updatedString = INPUT_STRING.replaceAll(DIGIT_REGEX, "");
    assertEquals(EXPECTED_STRING, updatedString);
  }
}
