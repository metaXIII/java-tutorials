package com.metaxiii.fr.model;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.junit.jupiter.api.Test;

class BeanWithFilterTest {

  @Test
  void whenSerializingUsingJsonFilter_thenCorrect() throws JsonProcessingException {
    BeanWithFilter bean = new BeanWithFilter(1, "My bean");
    FilterProvider filters = new SimpleFilterProvider()
      .addFilter("myFilter", SimpleBeanPropertyFilter.filterOutAllExcept("name"));
    String result = new ObjectMapper().writer(filters).writeValueAsString(bean);
    System.out.println(result);
    assertTrue(result.contains("My bean"));
    assertFalse(result.contains("id"));
  }
}
