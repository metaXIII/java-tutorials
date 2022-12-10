package com.metaxiii.fr.model;

import static org.junit.jupiter.api.Assertions.*;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

class BeanWithCustomAnnotationTest {

  @Test
  void whenSerializingUsingCustomAnnotation_thenCorrect()
    throws JsonProcessingException {
    BeanWithCustomAnnotation bean = new BeanWithCustomAnnotation(
      1,
      "My bean",
      null
    );
    String result = new ObjectMapper().writeValueAsString(bean);
    System.out.println(result);
    assertTrue(result.contains("My bean"));
    assertTrue(result.contains("1"));
    assertFalse(result.contains("dateCreated"));
  }
}
