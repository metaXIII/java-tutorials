package com.metaxiii.fr.conversion;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Map;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class JavaMapFromJSONStringTest {

  private JavaMapFromJSONString javaMapFromJSONString;
  private static final String JSON =
    "{\"color\": \"Black\", \"type\": \"BMW\"}";

  @BeforeEach
  public void init() {
    this.javaMapFromJSONString = new JavaMapFromJSONString();
  }

  @AfterEach
  public void endEach() {
    this.javaMapFromJSONString = null;
  }

  @Test
  void process() {
    assertDoesNotThrow(() -> {
      final Map<String, Object> cars = javaMapFromJSONString.process(JSON);
      assertEquals("[color, type]", cars.keySet().toString());
      assertEquals("[Black, BMW]", cars.values().toString());
    });
  }
}
