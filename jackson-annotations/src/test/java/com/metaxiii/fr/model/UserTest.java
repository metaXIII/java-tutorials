package com.metaxiii.fr.model;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

class UserTest {

  @Test
  void whenSerializingUsingJsonIgnoreType_thenCorrect() throws JsonProcessingException {
    User.Name name = new User.Name("John", "Doe");
    User user = new User(1, name);
    String result = new ObjectMapper().writeValueAsString(user);

    assertTrue(result.contains("1"));
    assertFalse(result.contains("name"));
    assertFalse(result.contains("John"));
  }
}
