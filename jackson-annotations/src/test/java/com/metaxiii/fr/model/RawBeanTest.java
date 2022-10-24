package com.metaxiii.fr.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class RawBeanTest {
    @Test
    void whenSerializingUsingJsonRawValue_thenCorrect()
            throws JsonProcessingException {
        RawBean bean = new RawBean("My bean", "{\"attr\":false}");
        String result = new ObjectMapper().writeValueAsString(bean);
        System.out.println(result);
        assertTrue(result.contains("My bean"));
        assertTrue(result.contains("{\"attr\":false}"));
    }

}
