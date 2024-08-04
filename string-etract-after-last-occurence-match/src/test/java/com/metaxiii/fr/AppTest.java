package com.metaxiii.fr;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class AppTest {

  private static final String INPUT1 = "a,   b,   c,   I need this value";
  private static final String EXPECTED1 = "I need this value";
  private static final String INPUT2 = "no-pattern-found";
  private static final String EXPECTED2 = "";
  private static final String INPUT3 = "a,   b,   c,   ";
  private static final String EXPECTED3 = "";

  @Test
  void itShouldFindBySubstring() {
    final String result1 = afterTheLastPatternBySubstring(INPUT1);
    assertEquals(EXPECTED1, result1);
    final String result2 = afterTheLastPatternBySubstring(INPUT2);
    assertEquals(EXPECTED2, result2);
    final String result3 = afterTheLastPatternBySubstring(INPUT3);
    assertEquals(EXPECTED3, result3);
  }

  private static String afterTheLastPatternBySubstring(final String input) {
    final int index = input.lastIndexOf(",   ");
    return index >= 0 ? input.substring(index + ",   ".length()) : "";
  }

  @Test
  void itShouldFindByUsingSplit() {
    final String result1 = afterTheLastPatternBySplit(INPUT1);
    assertEquals(EXPECTED1, result1);
    final String result2 = afterTheLastPatternBySplit(INPUT2);
    assertEquals(EXPECTED2, result2);
    final String result3 = afterTheLastPatternBySplit(INPUT3);
    assertEquals(EXPECTED3, result3);
  }

  private static String afterTheLastPatternBySplit(final String input) {
    final String[] arr = input.split(", {3}", -1);
    return (arr.length >= 2) ? arr[arr.length - 1] : "";
  }

  @Test
  void itShouldFindWithReplaceAll() {
    final String result1 = afterTheLastPatternByReplaceAll(INPUT1);
    assertEquals(EXPECTED1, result1);
    final String result2 = afterTheLastPatternByReplaceAll(INPUT2);
    assertEquals(EXPECTED2, result2);
    final String result3 = afterTheLastPatternByReplaceAll(INPUT3);
    assertEquals(EXPECTED3, result3);
  }

  private static String afterTheLastPatternByReplaceAll(final String input) {
    final String result = input.replaceAll(".*, {3}", "");
    return result.equals(input) ? "" : result;
  }
}
