package com.metaxiii.fr.conversion;

import com.metaxiii.fr.model.Car;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

class JavaListFromJSONArrayStringTest {

    private JavaListFromJSONArrayString javaListFromJSONArrayString;
    private static final String JSON_CAR_ARRAY = "[{\"color\": \"Black\", \"type\": \"BMW\"}, {\"color\": \"Red\", \"type\": \"Fiat\"}]";

    @BeforeEach
    public void init() {
        this.javaListFromJSONArrayString = new JavaListFromJSONArrayString();
    }

    @AfterEach
    public void endEach() {
        this.javaListFromJSONArrayString = null;
    }

    @Test
    void processAsList() {
        assertDoesNotThrow(() -> {
            final List<Car> cars = javaListFromJSONArrayString.processAsList(JSON_CAR_ARRAY);
            assertEquals("Black", cars.get(0).getColor());
            assertEquals("Red", cars.get(1).getColor());
        });
    }

    @Test
    void processAsArray() {
        assertDoesNotThrow(() -> {
            final Car[] cars = javaListFromJSONArrayString.processAsArray(JSON_CAR_ARRAY);
            assertEquals("Black", cars[0].getColor());
            assertEquals("Red", cars[1].getColor());
        });
    }
}
