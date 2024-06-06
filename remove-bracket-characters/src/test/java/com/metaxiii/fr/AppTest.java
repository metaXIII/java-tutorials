package com.metaxiii.fr;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;

class AppTest {

  private static final String INPUT = "This (is) <a> [nice] {string}!";
  private static final String EXPECTED = "This is a nice string!";
  private static final String INPUT_WITH_UNICODE = "⟨T⟩❰h❱「i」⦇s⦈ (is) <a> [nice] {string}!";

  @Test
  void itShouldReplaceWithRegex() {
    final String regex = "[(){}<>\\[\\]]";
    final String result = INPUT.replaceAll(regex, "");
    assertEquals(EXPECTED, result);
  }

  @Test
  void itShouldReplaceWithStringUtils() {
    final var result = StringUtils.replaceChars(INPUT, "(){}[]<>", null);
    assertEquals(EXPECTED, result);
  }

  @Test
  void itShouldReplaceWithUnicodeCharacter() {
    final String regex = "\\p{Ps}|\\p{Pe}|[<>]";
    final String result = INPUT.replaceAll(regex, "");
    assertEquals("This is a nice string!", result);
    final String resultWithUnicode = INPUT_WITH_UNICODE.replaceAll(regex, "");
    assertEquals(EXPECTED, resultWithUnicode);
  }

  @Test
  void itShouldReplaceWithUnicodeCharacterWithoutRemovingInferiorAsOrSuperiorAsChars() {
    final String regex = "\\p{Ps}|\\p{Pe}";
    final String result = INPUT.replaceAll(regex, "");
    assertEquals("This is <a> nice string!", result);
    final String resultWithUnicode = INPUT_WITH_UNICODE.replaceAll(regex, "");
    assertEquals("This is <a> nice string!", resultWithUnicode);
  }
}
