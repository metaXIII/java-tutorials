package com.metaxiii.fr;

import static com.metaxiii.fr.StringUtils.reverse;
import static org.junit.jupiter.api.Assertions.assertEquals;

import lombok.experimental.ExtensionMethod;
import org.junit.jupiter.api.Test;

@ExtensionMethod(StringUtils.class)
class StringUtilsUnitTest {

  @Test
  void givenString_whenUsingExtensionMethod_thenReverseString() {
    final String original = "Lombok Extension Method";
    final String reversed = original.reverse();
    assertEquals("dohteM noisnetxE kobmoL", reversed);
  }

  @Test
  void givenString_whenNotUsingExtensionMethod_thenReverseString() {
    final String original = "Lombok Extension Method";
    final String reversed = reverse(original);
    assertEquals("dohteM noisnetxE kobmoL", reversed);
  }
}
