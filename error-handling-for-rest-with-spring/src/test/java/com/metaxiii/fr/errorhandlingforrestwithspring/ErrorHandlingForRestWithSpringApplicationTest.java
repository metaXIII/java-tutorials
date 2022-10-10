package com.metaxiii.fr.errorhandlingforrestwithspring;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ErrorHandlingForRestWithSpringApplicationTest {
    @Test
    void contextLoads() {
        assertDoesNotThrow(() -> ErrorHandlingForRestWithSpringApplication.main(new String[]{}));
    }
}
