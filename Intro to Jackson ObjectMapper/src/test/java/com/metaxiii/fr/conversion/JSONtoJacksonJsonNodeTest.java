package com.metaxiii.fr.conversion;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.fasterxml.jackson.databind.JsonNode;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class JSONtoJacksonJsonNodeTest {

  private JSONtoJacksonJsonNode jSONtoJacksonJsonNode;
  private static final String JSON = "{\"color\":\"Black\",\"type\":\"BMW\"}";

  @BeforeEach
  public void init() {
    this.jSONtoJacksonJsonNode = new JSONtoJacksonJsonNode();
  }

  @AfterEach
  public void endEach() {
    this.jSONtoJacksonJsonNode = null;
  }

  @Test
  void process() {
    assertDoesNotThrow(() -> {
      final JsonNode process = jSONtoJacksonJsonNode.process(JSON);
      assertEquals("Black", process.get("color").asText());
    });
  }
}
