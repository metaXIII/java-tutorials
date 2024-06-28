package com.metaxiii.fr;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AppTest {

  private static final int[] NUMS = { 1, 2, 2, 3, 3, 4, 4, 4, 5 };
  private App app;

  @BeforeEach
  void beforeEach() {
    app = new App();
  }

  @Test
  void itShouldFindModeByFrequencyArray() {
    final var mode = app.findModeByFrequencyArray(NUMS);
    assertEquals(1, mode.size());
    assertDoesNotThrow(() -> mode.stream().filter(e -> e.equals(4)).findFirst().orElseThrow());
  }

  @Test
  void itShouldFindModeBySortingWithUsingSort() {
    final var mode = app.findModeBySorting(NUMS);
    assertEquals(1, mode.size());
    assertDoesNotThrow(() -> mode.stream().filter(e -> e.equals(4)).findFirst().orElseThrow());
    final var modeWithOnlyOneElement = app.findModeBySorting(new int[] { 1 });
    assertDoesNotThrow(() -> modeWithOnlyOneElement.stream().filter(e -> e.equals(1)).findFirst().orElseThrow());
  }

  @Test
  void itShouldFindModeByUsingStreams() {
    final var mode = app.findModeByUsingStreams(NUMS);
    assertEquals(1, mode.size());
    assertDoesNotThrow(() -> mode.stream().filter(e -> e.equals(4)).findFirst().orElseThrow());
  }

  @Test
  void itShouldFindModeByUsingTreemap() {
    final var mode = app.findModeByUsingTreemap(NUMS);
    assertEquals(1, mode.size());
    assertDoesNotThrow(() -> mode.stream().filter(e -> e.equals(4)).findFirst().orElseThrow());
  }
}
