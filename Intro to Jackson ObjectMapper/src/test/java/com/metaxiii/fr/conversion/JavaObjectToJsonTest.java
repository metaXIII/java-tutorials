package com.metaxiii.fr.conversion;

import com.metaxiii.fr.model.Car;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JavaObjectToJsonTest {

    private JavaObjectToJson javaObjectToJson;
    private static final String JSON = "{\"color\":\"Black\",\"type\":\"BMW\"}";

    @BeforeEach
    public void init() {
        this.javaObjectToJson = new JavaObjectToJson();
    }

    @AfterEach
    public void endEach() {
        this.javaObjectToJson = null;
    }

    @Test
    void itShouldRenderCarAsJson() {
        final Car car = new Car("Black", "BMW");
        assertDoesNotThrow(() -> {
            final String process = javaObjectToJson.process(car);
            assertEquals(JSON, process);
        });
    }
}
