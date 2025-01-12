package com.metaxiii.fr.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import org.junit.jupiter.api.Test;

class BeanWithCreatorTest {

  @Test
  void whenDeserializingUsingJsonCreator_thenCorrect() throws IOException {
    String json = "{\"custom_id\":1,\"custom_name\":\"My bean\"}";
    BeanWithCreator bean = new ObjectMapper().readerFor(BeanWithCreator.class).readValue(json);
    assertEquals("My bean", bean.getName());
  }
}
