package com.metaxiii.fr.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.BeanSerializerFactory;
import com.fasterxml.jackson.databind.ser.SerializerFactory;
import com.metaxiii.fr.config.MyBeanSerializerModifier;
import java.io.IOException;
import org.junit.jupiter.api.Test;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

class UserTest {

  @Test
  void whenUseJsonViewToSerialize_thenCorrect() throws JsonProcessingException {
    User user = new User(1, "John");
    Jackson2ObjectMapperBuilder mapperBuilder = new Jackson2ObjectMapperBuilder();
    final ObjectMapper mapper = mapperBuilder.build();
    String result = mapper.writerWithView(Views.Public.class).writeValueAsString(user);
    assertTrue(result.contains("John"));
    assertFalse(result.contains("1"));
  }

  @Test
  void whenUsePublicView_thenOnlyPublicSerialized() throws JsonProcessingException {
    Item item = new Item(2, "book", "John");
    ObjectMapper mapper = new ObjectMapper();
    String result = mapper.writerWithView(Views.Public.class).writeValueAsString(item);
    assertTrue(result.contains("book"));
    assertTrue(result.contains("2"));
    assertFalse(result.contains("John"));
  }

  @Test
  void whenUseInternalView_thenAllSerialized() throws JsonProcessingException {
    Item item = new Item(2, "book", "John");
    ObjectMapper mapper = new ObjectMapper();
    String result = mapper.writerWithView(Views.Internal.class).writeValueAsString(item);
    assertTrue(result.contains("book"));
    assertTrue(result.contains("2"));
    assertTrue(result.contains("John"));
  }

  @Test
  void whenUseJsonViewToDeserialize_thenCorrect() throws IOException {
    String json = "{\"id\":1,\"name\":\"John\"}";
    ObjectMapper mapper = new ObjectMapper();
    User user = mapper.readerWithView(Views.Public.class).forType(User.class).readValue(json);
    assertEquals(1, user.getId());
    assertEquals("John", user.getName());
  }

  @Test
  void whenUseCustomJsonViewToSerialize_thenCorrect() throws JsonProcessingException {
    User user = new User(1, "John");
    SerializerFactory serializerFactory = BeanSerializerFactory.instance.withSerializerModifier(
      new MyBeanSerializerModifier()
    );
    ObjectMapper mapper = new ObjectMapper();
    mapper.setSerializerFactory(serializerFactory);
    String result = mapper.writerWithView(Views.Public.class).writeValueAsString(user);
    assertTrue(result.contains("JOHN"));
    assertTrue(result.contains("1"));
  }
}
