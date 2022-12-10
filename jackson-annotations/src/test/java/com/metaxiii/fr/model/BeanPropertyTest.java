package com.metaxiii.fr.model;

import static org.junit.jupiter.api.Assertions.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import org.junit.jupiter.api.Test;

class BeanPropertyTest {

  @Test
  void whenUsingJsonProperty_thenCorrect() throws IOException {
    MyBean bean = new MyBean(1, "My bean");
    String result = new ObjectMapper().writeValueAsString(bean);
    System.out.println(result);
    assertTrue(result.contains("My bean"));
    assertTrue(result.contains("1"));
    MyBean resultBean = new ObjectMapper()
      .readerFor(MyBean.class)
      .readValue(result);
    assertEquals("My bean", resultBean.getName());
  }
}
