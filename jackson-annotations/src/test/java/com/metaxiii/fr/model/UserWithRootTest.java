package com.metaxiii.fr.model;

import static org.junit.jupiter.api.Assertions.assertTrue;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.jupiter.api.Test;

class UserWithRootTest {

  @Test
  void whenSerializingUsingJsonRootName_thenCorrect() throws JsonProcessingException {
    UserWithRoot user = new UserWithRoot(1, "John");
    ObjectMapper mapper = new ObjectMapper();
    mapper.enable(SerializationFeature.WRAP_ROOT_VALUE);
    String result = mapper.writeValueAsString(user);
    System.out.println(result);
    assertTrue(result.contains("John"));
    assertTrue(result.contains("user"));
  }
}
