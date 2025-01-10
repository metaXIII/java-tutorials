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

class CustomCarSerializerTest {

  private static final String JSON = "{\"car_brand\":\"renault\"}";
  private ObjectMapper mapper = new ObjectMapper();
  private SimpleModule module = new SimpleModule("CustomCarSerializer", new Version(1, 0, 0, null, null, null));

  @BeforeEach
  public void init() {
    this.mapper = new ObjectMapper();
    this.module = new SimpleModule("CustomCarSerializer", new Version(1, 0, 0, null, null, null));
  }

  @AfterEach
  public void endEach() {
    this.mapper = null;
    this.module = null;
  }

  @Test
  void serialize() {
    assertDoesNotThrow(() -> {
      module.addSerializer(Car.class, new CustomCarSerializer());
      mapper.registerModule(module);
      Car car = new Car("yellow", "renault");
      assertEquals(JSON, mapper.writeValueAsString(car));
    });
  }
}
