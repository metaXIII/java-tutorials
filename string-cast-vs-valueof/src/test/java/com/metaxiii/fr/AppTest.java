package com.metaxiii.fr;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;
import org.junit.jupiter.api.Test;

class AppTest {

  @Test
  @SuppressWarnings("CastCanBeRemovedNarrowingVariableType")
  void itShouldCast1ToStringAndStringValueOf() {
    final Object obj = "Java is cool";
    final String castResult = (String) obj;
    assertEquals("Java is cool", castResult);

    final String valueOfResult = String.valueOf(obj);
    assertEquals("Java is cool", valueOfResult);
  }

  @Test
  @SuppressWarnings({ "DataFlowIssue", "unused" })
  void itShouldCast2NonStringObject() {
    final Object obj = 42;
    assertThrows(
      ClassCastException.class,
      () -> {
        final String castResult = (String) obj;
      }
    );
    final Object obj2 = List.of("Java", "is", "cool");
    assertThrows(
      ClassCastException.class,
      () -> {
        final String castResult = (String) obj2;
      }
    );
    String valueOfResult = String.valueOf(obj);
    assertEquals("42", valueOfResult);
    valueOfResult = String.valueOf(obj2);
    assertEquals("[Java, is, cool]", valueOfResult);
  }

  @Test
  @SuppressWarnings({ "CastCanBeRemovedNarrowingVariableType", "ConstantValue" })
  void itShouldCast3HandleNullValues() {
    final Object obj = null;
    final String castResult = (String) obj;
    assertNull(castResult);
    final String valueOfResult = String.valueOf(obj);
    assertNotNull(valueOfResult);
    assertEquals("null", valueOfResult);
  }
}
