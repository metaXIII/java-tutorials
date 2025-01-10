package com.metaxiii.fr.model;

import static org.junit.jupiter.api.Assertions.assertTrue;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

class ItemWithIdentityTest {

  @Test
  void whenSerializingUsingJsonIdentityInfo_thenCorrect() throws JsonProcessingException {
    UserWithIdentity user = new UserWithIdentity(1, "John");
    ItemWithIdentity item = new ItemWithIdentity(2, "book", user);
    user.addItem(item);
    String result = new ObjectMapper().writeValueAsString(item);
    System.out.println(result);
    assertTrue(result.contains("book"));
    assertTrue(result.contains("John"));
    assertTrue(result.contains("userItems"));
  }
}
