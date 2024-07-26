package com.metaxiii.fr;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SpringwebclientVsResttemplateApplicationTest {

  @Test
  void itShouldMain() {
    assertDoesNotThrow(() -> SpringwebclientVsResttemplateApplication.main(new String[] {}));
  }
}
