package com.metaxiii.fr;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class AppTest {

  private static final String EXPECTED = "Java Tutorials and Guides at Baeldung. 😀";
  private static final int SMILEY_CODE_POINT = 0x1F600;

  @Test
  void givenCodePoint_whenConvertToEmoji_thenCorrectString() {
    final var textWithEmoji =
      "Java Tutorials and Guides at Baeldung. " + new String(Character.toChars(SMILEY_CODE_POINT));
    assertEquals(EXPECTED, textWithEmoji);
  }

  @Test
  @SuppressWarnings("StringBufferReplaceableByString")
  void givenStringBuilder_whenAppendEmoji_thenCorrectString() {
    final var sb = new StringBuilder("Java Tutorials and Guides at Baeldung. ");
    sb.append(Character.toChars(SMILEY_CODE_POINT));
    final var textWithEmoji = sb.toString();
    assertEquals(EXPECTED, textWithEmoji);
  }

  @Test
  void givenUnicodeEscape_whenInsertEmoji_thenCorrectString() {
    final var textWithEmoji = "Java Tutorials and Guides at Baeldung. \uD83D\uDE00";
    assertEquals(EXPECTED, textWithEmoji);
  }
}
