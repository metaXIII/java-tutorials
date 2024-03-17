package com.metaxiii.fr;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.Test;

class AppTest {

  @Test
  void itShouldApp() {
    assertDoesNotThrow(() -> App.main(new String[] {}));
  }
}
