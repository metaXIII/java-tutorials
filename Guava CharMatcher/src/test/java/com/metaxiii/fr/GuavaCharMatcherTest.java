package com.metaxiii.fr;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.google.common.base.CharMatcher;
import com.google.common.base.Predicate;
import java.nio.charset.Charset;
import org.junit.jupiter.api.Test;

class GuavaCharMatcherTest {

  @Test
  void whenCollapseFromString_thenCollapsed() {
    final var input = "       hel    lo      ";
    var result = CharMatcher.is(' ').collapseFrom(input, '-');
    assertEquals("-hel-lo-", result);
    result = CharMatcher.is(' ').trimAndCollapseFrom(input, '-');
    assertEquals("hel-lo", result);
  }

  @Test
  void whenCountCharInString_thenCorrect() {
    final var input = "a, c, z, 1, 2";
    var result = CharMatcher.is(',').countIn(input);
    assertEquals(4, result);
    result = CharMatcher.inRange('a', 'h').countIn(input);
    assertEquals(2, result);
  }

  @Test
  void whenRemoveCharsNotInCharset_thenRemoved() {
    final var charset = Charset.forName("cp437");
    final var encoder = charset.newEncoder();
    final var inRange = new Predicate<Character>() {

      @Override
      public boolean apply(final Character c) {
        return encoder.canEncode(c);
      }
    };
    final var result = CharMatcher.forPredicate(inRange).retainFrom("helloは");
    assertEquals("hello", result);
  }

  @Test
  void whenRemoveNonASCIIChars_thenRemoved() {
    final var input = "あhello₤";
    var result = CharMatcher.ascii().retainFrom(input);
    assertEquals("hello", result);
    result = CharMatcher.inRange('0', 'z').retainFrom(input);
    assertEquals("hello", result);
  }

  @Test
  void whenRemoveSpecialCharacters_thenRemoved() {
    final var input = "H:*el.lo,}12";
    final var matcher = CharMatcher.forPredicate(Character::isLetterOrDigit);
    final var result = matcher.retainFrom(input);
    assertEquals("Hello12", result);
  }

  @Test
  void whenReplaceFromString_thenReplaced() {
    final var input = "apple-banana.";
    var result = CharMatcher.anyOf("-.").replaceFrom(input, '!');
    assertEquals("apple!banana!", result);
    result = CharMatcher.is('-').replaceFrom(input, " and ");
    assertEquals("apple and banana.", result);
  }

  @Test
  void whenTrimString_thenTrimmed() {
    final var input = "---hello,,,";
    var result = CharMatcher.is('-').trimLeadingFrom(input);
    assertEquals("hello,,,", result);
    result = CharMatcher.is(',').trimTrailingFrom(input);
    assertEquals("---hello", result);
    result = CharMatcher.anyOf("-,").trimFrom(input);
    assertEquals("hello", result);
  }

  @Test
  void whenValidateString_thenValid() {
    final var input = "hello";
    var result = CharMatcher.ascii().matchesAllOf(input);
    assertTrue(result);
    result = CharMatcher.is('e').matchesAnyOf(input);
    assertTrue(result);
    result = CharMatcher.inRange('0', '9').matchesNoneOf(input);
    assertTrue(result);
  }
}
