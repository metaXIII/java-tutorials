package com.metaxiii.fr.model;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

class UserWithRefTest {

  @Test
  void whenSerializingUsingJacksonReferenceAnnotation_thenCorrect() throws JsonProcessingException {
    UserWithRef user = new UserWithRef(1, "John");
    ItemWithRef item = new ItemWithRef(2, "book", user);
    user.addItem(item);
    String result = new ObjectMapper().writeValueAsString(item);
    System.out.println(result);
    assertTrue(result.contains("book"));
    assertTrue(result.contains("John"));
    assertFalse(result.contains("userItems"));
  }
}
