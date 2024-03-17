package com.metaxiii.fr;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class AppTest {

  @Test
  void itShouldCountWithBitManipulation() {
    final var testedNumber = 1133;
    final var expectedWithErrors = 0;
    assertEquals(1, App.countWithBitManipulation(expectedWithErrors));
    assertEquals(2, App.countWithBitManipulation(testedNumber));
  }

  @Test
  void itShouldCountWithSet() {
    final var testedNumber = 1133;
    assertEquals(2, App.countWithSet(testedNumber));
  }

  @Test
  void itShouldCountWithStreamApi() {
    final var testedNumber = 1133;
    assertEquals(2, App.countWithStreamApi(testedNumber));
  }

  @Test
  void itShouldMain() {
    assertDoesNotThrow(() -> App.main(new String[] {}));
  }
}
