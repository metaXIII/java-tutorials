package com.metaxiii.fr;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ApplicationTest {

  @Test
  void contextLoads() {
    assertDoesNotThrow(() -> Application.main(new String[] {}));
  }
}
