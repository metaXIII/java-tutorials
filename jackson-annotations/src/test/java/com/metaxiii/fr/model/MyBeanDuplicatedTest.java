package com.metaxiii.fr.model;

import static org.junit.jupiter.api.Assertions.assertTrue;

import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.json.JsonMapper;
import java.io.IOException;
import org.junit.jupiter.api.Test;

class MyBeanDuplicatedTest {

  @Test
  void whenDisablingAllAnnotations_thenAllDisabled() throws IOException {
    MyBean bean = new MyBean(1, null);
    final JsonMapper mapper = JsonMapper
      .builder()
      .disable(MapperFeature.USE_ANNOTATIONS)
      .build();
    String result = mapper.writeValueAsString(bean);
    System.out.println(result);
    assertTrue(result.contains("1"));
    assertTrue(result.contains("name"));
  }
}
