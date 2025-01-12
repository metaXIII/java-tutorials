package com.metaxiii.fr.enums;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import org.junit.jupiter.api.Test;

class TypeEnumWithValueTest {

  @Test
  void whenSerializingUsingJsonValue_thenCorrect() throws IOException {
    String enumAsString = new ObjectMapper().writeValueAsString(TypeEnumWithValue.TYPE1);
    assertEquals("\"Type A\"", enumAsString);
  }
}
