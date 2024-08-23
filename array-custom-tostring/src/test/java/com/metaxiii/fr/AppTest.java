package com.metaxiii.fr;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.google.common.base.Joiner;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;

class AppTest {

  private static final String[] CONTENT = new String[] { "www.", "Baeldung.", "com" };

  @Test
  void givenArray_whenUsingJoinerJoin_thenPrintedArrayWithoutCommaBrackets() {
    final String result = Joiner.on("").join(CONTENT);
    assertEquals("www.Baeldung.com", result);
  }

  @Test
  @SuppressWarnings("SimplifyStreamApiCallChains")
  void givenArray_whenUsingStream_thenPrintedArrayWithoutCommaBrackets() {
    final String result = Stream.of(CONTENT).collect(Collectors.joining(""));
    assertEquals("www.Baeldung.com", result);
  }

  @Test
  void givenArray_whenUsingStringBuilder_thenPrintedArrayWithoutCommaBrackets() {
    final StringBuilder builder = new StringBuilder();
    for (final String element : CONTENT) {
      builder.append(element);
    }
    assertEquals("www.Baeldung.com", builder.toString());
  }

  @Test
  void givenArray_whenUsingStringJoin_thenPrintedArrayWithoutCommaBrackets() {
    final String result = String.join("", CONTENT);
    assertEquals("www.Baeldung.com", result);
  }

  @Test
  void givenArray_whenUsingStringReplaceAll_thenPrintedArrayWithoutCommaBrackets() {
    final String result = Arrays.toString(CONTENT).replaceAll("\\[|]|, ", "");
    assertEquals("www.Baeldung.com", result);
  }

  @Test
  void givenArray_whenUsingStringReplace_thenPrintedArrayWithoutCommaBrackets() {
    final String result = Arrays.toString(CONTENT).replace("[", "").replace("]", "").replace(", ", "");
    assertEquals("www.Baeldung.com", result);
  }

  @Test
  void givenArray_whenUsingStringUtilsJoin_thenPrintedArrayWithoutCommaBrackets() {
    final String result = StringUtils.join(CONTENT, "");
    assertEquals("www.Baeldung.com", result);
  }
}
