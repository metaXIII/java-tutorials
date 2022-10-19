package com.metaxiii.fr;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@SpringBootTest
@DirtiesContext
class SpringwebclientVsResttemplateApplicationTests {

    @Test
    void contextLoads() {
        assertDoesNotThrow(() -> SpringwebclientVsResttemplateApplication.main(new String[]{}));
    }

}
