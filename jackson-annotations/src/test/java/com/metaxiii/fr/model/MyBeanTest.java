package com.metaxiii.fr.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import org.junit.jupiter.api.Test;

class MyBeanTest {

  @Test
  void whenDeserializingUsingJsonSetter_thenCorrect() throws IOException {
    String json = "{\"id\":1,\"other\":\"My bean\"}";
    MyBean bean = new ObjectMapper().readerFor(MyBean.class).readValue(json);
    assertEquals("My bean", bean.getName());
  }

  @Test
  void whenSerializingUsingJsonGetter_thenCorrect() throws JsonProcessingException {
    MyBean bean = new MyBean(1, "My bean");
    String result = new ObjectMapper().writeValueAsString(bean);
    System.out.println(result);
    assertTrue(result.contains("My bean"));
    assertTrue(result.contains("1"));
  }

  @Test
  void whenSerializingUsingJsonInclude_thenCorrect() throws JsonProcessingException {
    MyBean bean = new MyBean(1, null);
    String result = new ObjectMapper().writeValueAsString(bean);
    System.out.println(result);
    assertTrue(result.contains("1"));
    assertFalse(result.contains("name"));
  }
}
