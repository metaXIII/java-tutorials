package com.metaxiii.fr;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.google.common.base.Function;
import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import java.util.List;
import org.junit.jupiter.api.Test;

class GuavaSplitAndJoinTest {

  @Test
  void whenConvertListToString_thenConverted() {
    final var names = Lists.newArrayList("John", "Jane", "Adam", "Tom");
    final var result = Joiner.on(",").join(names);
    assertEquals("John,Jane,Adam,Tom", result);
  }

  @Test
  void whenConvertMapToString_thenConverted() {
    final var salary = Maps.newHashMap();
    salary.put("John", 1000);
    salary.put("Jane", 1500);
    final var result = Joiner.on(" , ").withKeyValueSeparator(" = ").join(salary);
    assertTrue(result.contains("John = 1000"));
    assertTrue(result.contains("Jane = 1500"));
  }

  @SuppressWarnings("StaticPseudoFunctionalStyleMethod")
  @Test
  void whenJoinNestedCollections_thenJoined() {
    final var nested = Lists.newArrayList(
      Lists.newArrayList("apple", "banana", "orange"),
      Lists.newArrayList("cat", "dog", "bird"),
      Lists.newArrayList("John", "Jane", "Adam")
    );
    final var result = Joiner
      .on(";")
      .join(Iterables.transform(nested, (Function<List<String>, String>) input -> Joiner.on("-").join(input)));

    assertTrue(result.contains("apple-banana-orange"));
    assertTrue(result.contains("cat-dog-bird"));
    assertTrue(result.contains("John-Jane-Adam"));
  }

  @Test
  void whenConvertListToStringAndSkipNull_thenConverted() {
    final var names = Lists.newArrayList("John", null, "Jane", "Adam", "Tom");
    final var result = Joiner.on(",").skipNulls().join(names);
    assertEquals("John,Jane,Adam,Tom", result);
  }

  @Test
  void whenUseForNull_thenUsed() {
    final var names = Lists.newArrayList("John", null, "Jane", "Adam", "Tom");
    final var result = Joiner.on(",").useForNull("nameless").join(names);
    assertEquals("John,nameless,Jane,Adam,Tom", result);
  }

  @Test
  void whenCreateListFromString_thenCreated() {
    final var input = "apple - banana - orange";
    final var result = Splitter.on("-").trimResults().splitToList(input);
    assertTrue(result.containsAll(List.of("apple", "banana", "orange")));
  }

  @Test
  @SuppressWarnings("UnstableApiUsage")
  void whenCreateMapFromString_thenCreated() {
    final var input = "John=first,Adam=second";
    final var result = Splitter.on(",").withKeyValueSeparator("=").split(input);
    assertEquals("first", result.get("John"));
    assertEquals("second", result.get("Adam"));
  }

  @Test
  void whenSplitStringOnMultipleSeparator_thenSplit() {
    final var input = "apple.banana,,orange,,.";
    final var result = Splitter.onPattern("[.,]").omitEmptyStrings().splitToList(input);
    assertTrue(result.containsAll(List.of("apple", "banana", "orange")));
  }

  @Test
  void whenSplitStringOnSpecificLength_thenSplit() {
    final var input = "Hello world";
    final var result = Splitter.fixedLength(3).splitToList(input);
    assertTrue(result.containsAll(List.of("Hel", "lo ", "wor", "ld")));
  }

  @Test
  void whenLimitSplitting_thenLimited() {
    final var input = "a,b,c,d,e";
    final var result = Splitter.on(",").limit(4).splitToList(input);
    assertEquals(4, result.size());
    assertTrue(result.containsAll(List.of("a", "b", "c", "d,e")));
  }
}
