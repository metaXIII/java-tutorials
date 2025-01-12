package com.metaxiii.fr.model;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

class ItemTest {

  @Test
  void whenSerializingUsingJsonView_thenCorrect() throws JsonProcessingException {
    Item item = new Item(2, "book", "John");
    String result = new ObjectMapper().writerWithView(Views.Public.class).writeValueAsString(item);
    System.out.println(result);
    assertTrue(result.contains("book"));
    assertTrue(result.contains("2"));
    assertFalse(result.contains("John"));
  }

  @Test
  void whenSerializingUsingMixInAnnotation_thenCorrect() throws JsonProcessingException {
    DuplicateItem item = new DuplicateItem(1, "book", null);
    String result = new ObjectMapper().writeValueAsString(item);
    System.out.println(result);
    assertTrue(result.contains("owner"));
    ObjectMapper mapper = new ObjectMapper();
    mapper.addMixIn(User.class, MyMixInForIgnoreType.class);
    result = mapper.writeValueAsString(item);
    System.out.println(result);
    assertFalse(result.contains("owner"));
  }
}
