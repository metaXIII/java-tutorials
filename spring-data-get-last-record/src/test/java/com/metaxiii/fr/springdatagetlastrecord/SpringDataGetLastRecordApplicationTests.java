package com.metaxiii.fr.springdatagetlastrecord;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringDataGetLastRecordApplicationTests {

  @Test
  void contextLoads() {
    Assertions.assertDoesNotThrow(() -> SpringDataGetLastRecordApplication.main(new String[] {}));
  }
}
