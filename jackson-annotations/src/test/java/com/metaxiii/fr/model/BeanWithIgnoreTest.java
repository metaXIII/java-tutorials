package com.metaxiii.fr.model;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

class BeanWithIgnoreTest {

  @Test
  void whenSerializingUsingJsonIgnoreProperties_thenCorrect()
    throws JsonProcessingException {
    BeanWithIgnore bean = new BeanWithIgnore(1, "My bean", 1);
    String result = new ObjectMapper().writeValueAsString(bean);
    assertTrue(result.contains("My bean"));
    assertFalse(result.contains("id"));
  }

  @Test
  void whenSerializingUsingJsonIgnore_thenCorrect()
    throws JsonProcessingException {
    BeanWithIgnore bean = new BeanWithIgnore(1, "My bean", 1);
    String result = new ObjectMapper().writeValueAsString(bean);
    System.out.println(result);
    assertTrue(result.contains("My bean"));
    assertFalse(result.contains("other_id"));
  }
}
