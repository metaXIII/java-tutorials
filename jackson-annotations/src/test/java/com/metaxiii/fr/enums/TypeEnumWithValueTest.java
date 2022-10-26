package com.metaxiii.fr.enums;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TypeEnumWithValueTest {
    @Test
    void whenSerializingUsingJsonValue_thenCorrect()
            throws IOException {
        String enumAsString = new ObjectMapper()
                .writeValueAsString(TypeEnumWithValue.TYPE1);
        assertEquals("\"Type A\"", enumAsString);
    }

}
