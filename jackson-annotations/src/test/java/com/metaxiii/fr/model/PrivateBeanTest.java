package com.metaxiii.fr.model;

import static org.junit.jupiter.api.Assertions.assertTrue;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

class PrivateBeanTest {

  @Test
  void whenSerializingUsingJsonAutoDetect_thenCorrect() throws JsonProcessingException {
    PrivateBean bean = new PrivateBean(1, "My bean");
    String result = new ObjectMapper().writeValueAsString(bean);
    System.out.println(result);
    assertTrue(result.contains("1"));
    assertTrue(result.contains("My bean"));
  }
}
