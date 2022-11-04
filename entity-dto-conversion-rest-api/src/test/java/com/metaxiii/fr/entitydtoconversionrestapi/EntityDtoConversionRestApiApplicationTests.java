package com.metaxiii.fr.entitydtoconversionrestapi;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class EntityDtoConversionRestApiApplicationTests {

    @Test
    void contextLoads() {
        Assertions.assertDoesNotThrow(() -> EntityDtoConversionRestApiApplication.main(new String[]{}));
    }

}
