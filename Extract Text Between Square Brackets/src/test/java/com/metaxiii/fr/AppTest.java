package com.metaxiii.fr;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.junit.jupiter.api.Test;

class AppTest {

  static final String INPUT1 = "some text [THE IMPORTANT MESSAGE] something else";
  static final String EXPECTED1 = "THE IMPORTANT MESSAGE";

  static final String INPUT2 = "[La La Land], [The last Emperor], and [Life of Pi] are all great movies.";
  static final List<String> EXPECTED2 = List.of("La La Land", "The last Emperor", "Life of Pi");

  @Test
  void whenUsingCharClassOnInput1_thenGetExpectedResult() {
    String result = null;
    String rePattern = "\\[([^]]*)";
    Pattern p = Pattern.compile(rePattern);
    Matcher m = p.matcher(INPUT1);
    if (m.find()) {
      result = m.group(1);
    }
    assertEquals(EXPECTED1, result);
  }

  @Test
  void whenUsingCharClassOnInput2_thenGetExpectedResult() {
    List<String> result = new ArrayList<>();
    String rePattern = "\\[([^]]*)";
    Pattern p = Pattern.compile(rePattern);
    Matcher m = p.matcher(INPUT2);
    while (m.find()) {
      result.add(m.group(1));
    }
    assertEquals(EXPECTED2, result);
  }

  @Test
  void whenUsingDotStarOnInput1_thenGetExpectedResult() {
    String result = null;
    String rePattern = "\\[(.*)]";
    Pattern p = Pattern.compile(rePattern);
    Matcher m = p.matcher(INPUT1);
    if (m.find()) {
      result = m.group(1);
    }
    assertEquals(EXPECTED1, result);
  }

  @Test
  void whenUsingNonGreedyOnInput2_thenGetExpectedResult() {
    List<String> result = new ArrayList<>();
    String rePattern = "\\[(.*?)]";
    Pattern p = Pattern.compile(rePattern);
    Matcher m = p.matcher(INPUT2);
    while (m.find()) {
      result.add(m.group(1));
    }
    assertEquals(EXPECTED2, result);
  }

  @Test
  void whenUsingSplitInput2_thenGetExpectedResult() {
    List<String> result = new ArrayList<>();
    String[] strArray = INPUT2.split("[\\[\\]]", -1);
    for (int i = 1; i < strArray.length; i += 2) {
      result.add(strArray[i]);
    }
    assertEquals(EXPECTED2, result);
  }

  @Test
  void whenUsingSplitOnInput1_thenGetExpectedResult() {
    String[] strArray = INPUT1.split("[\\[\\]]", -1);
    String result = strArray.length == 3 ? strArray[1] : null;
    assertEquals(EXPECTED1, result);
  }

  @Test
  void whenUsingSplitWithLimit_thenGetExpectedResult() {
    String[] strArray = "[THE IMPORTANT MESSAGE]".split("[\\[\\]]");
    assertEquals(2, strArray.length);
    assertTrue(Arrays.stream(strArray).toList().containsAll(List.of("", "THE IMPORTANT MESSAGE")));
    strArray = "[THE IMPORTANT MESSAGE]".split("[\\[\\]]", -1);
    assertEquals(3, strArray.length);
    assertTrue(Arrays.stream(strArray).toList().containsAll(List.of("", "THE IMPORTANT MESSAGE", "")));
  }
}
