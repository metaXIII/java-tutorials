package com.metaxiii.fr.conversion;

import static org.junit.jupiter.api.Assertions.*;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import com.metaxiii.fr.model.Car;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SerializationDeserializationTest {

  private SerializationDeserialization serializationDeserialization;
  private static final String JSON =
    "{ \"color\" : \"Black\", \"type\" : \"BMW\", \"year\" : \"2022\" }";
  private static final String GOOD_JSON =
    "{ \"color\" : \"Black\", \"type\" : \"BMW\"}";

  @BeforeEach
  public void init() {
    this.serializationDeserialization = new SerializationDeserialization();
  }

  @AfterEach
  public void endEach() {
    this.serializationDeserialization = null;
  }

  @Test
  void itShouldThrowsUnrecognizedPropertyExceptionWhenObjectMapperIsNotConfigureForDeserialization() {
    assertThrows(
      UnrecognizedPropertyException.class,
      () -> {
        final Car car = serializationDeserialization.processWithUnrecognizedPropertyException(
          JSON
        );
        assertNull(car);
      }
    );
  }

  @Test
  void itShouldNotThrowsUnrecognizedPropertyExceptionWhenObjectMapperIsNotConfigureForDeserialization() {
    assertDoesNotThrow(() -> {
      final Car car = serializationDeserialization.processWithUnrecognizedPropertyException(
        GOOD_JSON
      );
      assertNotNull(car);
    });
  }

  @Test
  void itShouldProcessWhenObjectMapperIsConfigure() {
    assertDoesNotThrow(() -> {
      final Car car = serializationDeserialization.process(JSON);
      assertEquals("Black", car.getColor());
      assertEquals("BMW", car.getType());
    });
  }

  @Test
  void itShouldRenderJsonNode() {
    assertDoesNotThrow(() -> {
      final JsonNode jsonNodeRoot = serializationDeserialization.processJsonNode(
        JSON
      );
      JsonNode jsonNodeYear = jsonNodeRoot.get("year");
      assertEquals("2022", jsonNodeYear.asText());
    });
  }
}
