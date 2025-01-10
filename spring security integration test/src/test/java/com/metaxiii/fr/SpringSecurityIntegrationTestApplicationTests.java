package com.metaxiii.fr;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringSecurityIntegrationTestApplicationTests {

  @Test
  void contextLoads() {
    assertDoesNotThrow(() -> SpringSecurityIntegrationTestApplication.main(new String[] {}));
  }
}
