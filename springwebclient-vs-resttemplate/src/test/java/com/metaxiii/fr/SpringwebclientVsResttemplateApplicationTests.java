package com.metaxiii.fr;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

@SpringBootTest
@DirtiesContext
class SpringwebclientVsResttemplateApplicationTests {

  @Test
  void contextLoads() {
    assertDoesNotThrow(() ->
      SpringwebclientVsResttemplateApplication.main(new String[] {})
    );
  }
}
