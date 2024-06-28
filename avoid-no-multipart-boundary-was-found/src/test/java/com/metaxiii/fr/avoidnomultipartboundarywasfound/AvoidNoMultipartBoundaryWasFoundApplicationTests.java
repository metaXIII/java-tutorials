package com.metaxiii.fr.avoidnomultipartboundarywasfound;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AvoidNoMultipartBoundaryWasFoundApplicationTests {

  @Test
  void contextLoads() {
    Assertions.assertDoesNotThrow(() -> AvoidNoMultipartBoundaryWasFoundApplication.main(new String[] {}));
  }
}
