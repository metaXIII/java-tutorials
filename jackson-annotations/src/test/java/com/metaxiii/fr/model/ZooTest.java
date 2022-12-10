package com.metaxiii.fr.model;

import static org.junit.jupiter.api.Assertions.*;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import org.junit.jupiter.api.Test;

class ZooTest {

  @Test
  void whenSerializingPolymorphic_thenCorrect() throws JsonProcessingException {
    Zoo.Dog dog = new Zoo.Dog("lacy");
    Zoo zoo = new Zoo(dog);
    String result = new ObjectMapper().writeValueAsString(zoo);
    System.out.println(result);
    assertTrue(result.contains("type"));
    assertTrue(result.contains("dog"));
  }

  @Test
  void whenDeserializingPolymorphic_thenCorrect() throws IOException {
    String json = "{\"animal\":{\"name\":\"lacy\",\"type\":\"cat\"}}";
    Zoo zoo = new ObjectMapper().readerFor(Zoo.class).readValue(json);
    assertEquals("lacy", zoo.animal.name);
    assertEquals(Zoo.Cat.class, zoo.animal.getClass());
  }
}
