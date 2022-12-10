package com.metaxiii.fr.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.fasterxml.jackson.databind.InjectableValues;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import org.junit.jupiter.api.Test;

class BeanWithInjectTest {

  @Test
  void whenDeserializingUsingJsonInject_thenCorrect() throws IOException {
    String json = "{\"name\":\"My bean\"}";
    InjectableValues inject = new InjectableValues.Std().addValue(int.class, 1);
    BeanWithInject bean = new ObjectMapper()
      .reader(inject)
      .forType(BeanWithInject.class)
      .readValue(json);
    assertEquals("My bean", bean.getName());
    assertEquals(1, bean.getId());
  }
}
