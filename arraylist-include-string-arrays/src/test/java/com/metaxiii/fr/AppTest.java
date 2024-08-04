package com.metaxiii.fr;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;

class AppTest {

  private static final String[] ARRAY1 = { "Java", "Kotlin", "Sql", "Javascript" };
  private static final String[] ARRAY2 = { "C", "C++", "C#", "Typescript" };
  private static final String[] ARRAY3 = { "Python", "Ruby", "Go", "Rust" };

  private static final List<String> EXPECTED = List.of(
    "Languages",
    ":",
    "Java",
    "Kotlin",
    "Sql",
    "Javascript",
    "C",
    "C++",
    "C#",
    "Typescript",
    "Python",
    "Ruby",
    "Go",
    "Rust"
  );

  @Test
  void whenConvertArrayToListThenAddAll_thenCorrect() {
    final List<String> languageList = initLanguageList();
    for (final String[] array : List.of(ARRAY1, ARRAY2, ARRAY3)) {
      languageList.addAll(Arrays.asList(array));
    }
    assertEquals(EXPECTED, languageList);
  }

  private List<String> initLanguageList() {
    final List<String> languageList = new ArrayList<>();
    languageList.add("Languages");
    languageList.add(":");
    return languageList;
  }

  @Test
  void whenUsingCollectionsAddAll_thenCorrect() {
    final List<String> languageList = initLanguageList();
    for (final String[] array : List.of(ARRAY1, ARRAY2, ARRAY3)) {
      Collections.addAll(languageList, array);
    }
    assertEquals(EXPECTED, languageList);
  }

  @Test
  void whenUsingStream_thenCorrect() {
    final List<String> languageList = initLanguageList();
    Stream.of(ARRAY1, ARRAY2, ARRAY3).flatMap(Arrays::stream).forEachOrdered(languageList::add);
    assertEquals(EXPECTED, languageList);
  }
}
