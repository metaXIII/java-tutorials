package com.metaxiii.fr.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import org.junit.jupiter.api.Test;

class ExtendableBeanTest {

  @Test
  void whenSerializingUsingJsonAnyGetter_thenCorrect() throws JsonProcessingException {
    ExtendableBean bean = new ExtendableBean("My bean");
    bean.add("attr1", "val1");
    bean.add("attr2", "val2");
    String result = new ObjectMapper().writeValueAsString(bean);
    System.out.println(result);
    assertTrue(result.contains("attr1"));
    assertTrue(result.contains("val1"));
  }

  @Test
  void whenDeserializingUsingJsonAnySetter_thenCorrect() throws IOException {
    String json = "{\"name\":\"My bean\",\"attr2\":\"val2\",\"attr1\":\"val1\"}";
    ExtendableBean bean = new ObjectMapper().readerFor(ExtendableBean.class).readValue(json);
    assertEquals("My bean", bean.getName());
    assertEquals("val2", bean.getProperties().get("attr2"));
  }
}
