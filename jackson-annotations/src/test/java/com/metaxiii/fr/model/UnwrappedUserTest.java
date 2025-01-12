package com.metaxiii.fr.model;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

class UnwrappedUserTest {

  @Test
  void whenSerializingUsingJsonUnwrapped_thenCorrect() throws JsonProcessingException {
    UnwrappedUser.Name name = new UnwrappedUser.Name("John", "Doe");
    UnwrappedUser user = new UnwrappedUser(1, name);
    String result = new ObjectMapper().writeValueAsString(user);
    System.out.println(result);
    assertTrue(result.contains("John"));
    assertFalse(result.contains("name"));
  }
}
