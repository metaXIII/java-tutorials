package com.metaxiii.fr;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.junit.jupiter.api.Test;

class AppTest {

  private static final String INPUT =
    "This line contains <the first value>, <the second value>, and <the third value>.";

  @Test
  void whenCallingMatcherEnd_thenGetIndexesAfterTheMatchSequence() {
    Pattern pattern = Pattern.compile("456");
    Matcher matcher = pattern.matcher("0123456789");
    String result = null;
    int startIdx = -1;
    int endIdx = -1;
    if (matcher.find()) {
      result = matcher.group();
      startIdx = matcher.start();
      endIdx = matcher.end();
    }
    assertEquals("456", result);
    assertEquals(4, startIdx);
    assertEquals(7, endIdx);
  }

  @Test
  void whenUsingMatcherStartAndEndWithGroupIdx_thenGetIndexesOfMatches() {
    Pattern pattern = Pattern.compile("<([^>]*)>");
    Matcher matcher = pattern.matcher(INPUT);
    List<String> result = new ArrayList<>();
    Map<Integer, Integer> indexesOfMatches = new LinkedHashMap<>();
    while (matcher.find()) {
      result.add(matcher.group(1));
      indexesOfMatches.put(matcher.start(1), matcher.end(1));
    }
    assertTrue(result.containsAll(List.of("the first value", "the second value", "the third value")));
    assertTrue(
      indexesOfMatches
        .entrySet()
        .stream()
        .map(entry -> INPUT.substring(entry.getKey(), entry.getValue()))
        .toList()
        .containsAll(List.of("the first value", "the second value", "the third value"))
    );
  }

  @Test
  void whenUsingMatcherStartAndEnd_thenGetIndexesOfMatches() {
    Pattern pattern = Pattern.compile("<[^>]*>");
    Matcher matcher = pattern.matcher(INPUT);
    List<String> result = new ArrayList<>();
    Map<Integer, Integer> indexesOfMatches = new LinkedHashMap<>();
    while (matcher.find()) {
      result.add(matcher.group());
      indexesOfMatches.put(matcher.start(), matcher.end());
    }
    assertTrue(result.containsAll(List.of("<the first value>", "<the second value>", "<the third value>")));
    assertTrue(
      indexesOfMatches
        .entrySet()
        .stream()
        .map(entry -> INPUT.substring(entry.getKey(), entry.getValue()))
        .toList()
        .containsAll(List.of("<the first value>", "<the second value>", "<the third value>"))
    );
  }

  @Test
  void whenUsingNorCharClass_thenGetExpectedTexts() {
    Pattern pattern = Pattern.compile("<[^>]*>");
    Matcher matcher = pattern.matcher(INPUT);
    List<String> result = new ArrayList<>();
    while (matcher.find()) {
      result.add(matcher.group());
    }
    assertTrue(result.containsAll(List.of("<the first value>", "<the second value>", "<the third value>")));
  }
}
