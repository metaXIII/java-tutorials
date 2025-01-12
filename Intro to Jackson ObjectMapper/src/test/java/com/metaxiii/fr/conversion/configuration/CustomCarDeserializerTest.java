package com.metaxiii.fr.conversion.configuration;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.metaxiii.fr.model.Car;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CustomCarDeserializerTest {

  private static final String JSON = "{ \"color\" : \"Black\", \"type\" : \"BMW\"}";
  private ObjectMapper mapper = new ObjectMapper();
  private SimpleModule module = new SimpleModule("CustomCarDeserializer", new Version(1, 0, 0, null, null, null));

  @BeforeEach
  public void init() {
    this.mapper = new ObjectMapper();
    this.module = new SimpleModule("CustomCarDeserializer", new Version(1, 0, 0, null, null, null));
  }

  @AfterEach
  public void endEach() {
    this.mapper = null;
    this.module = null;
  }

  @Test
  void itShouldConstructor() {
    assertDoesNotThrow(() -> new CustomCarDeserializer());
    assertDoesNotThrow(() -> new CustomCarDeserializer(Car.class));
  }

  @Test
  void deserialize() {
    assertDoesNotThrow(() -> {
      ObjectMapper deserializeMapper = new ObjectMapper();
      SimpleModule deserializeModule = new SimpleModule(
        "CustomCarDeserializer",
        new Version(1, 0, 0, null, null, null)
      );
      deserializeModule.addDeserializer(Car.class, new CustomCarDeserializer());
      deserializeMapper.registerModule(module);
      final Car car = mapper.readValue(JSON, Car.class);
      assertEquals("Black", car.getColor());
      assertEquals("BMW", car.getType());
    });
  }
}
