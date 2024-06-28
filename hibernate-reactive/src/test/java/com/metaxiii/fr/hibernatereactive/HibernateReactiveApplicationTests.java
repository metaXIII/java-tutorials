package com.metaxiii.fr.hibernatereactive;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class HibernateReactiveApplicationTests {

  @Test
  void contextLoads() {
    assertDoesNotThrow(() -> HibernateReactiveApplication.main(new String[] {}));
  }
}
