package com.metaxiii.fr.errorhandlingforrestwithspring;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ErrorHandlingForRestWithSpringApplicationTest {

  @Test
  void contextLoads() {
    assertDoesNotThrow(() -> ErrorHandlingForRestWithSpringApplication.main(new String[] {}));
  }
}
