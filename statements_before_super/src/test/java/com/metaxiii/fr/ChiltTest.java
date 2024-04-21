package com.metaxiii.fr;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.Test;

class ChiltTest {

  @Test
  void test() {
    assertDoesNotThrow(Child::new);
  }
}
