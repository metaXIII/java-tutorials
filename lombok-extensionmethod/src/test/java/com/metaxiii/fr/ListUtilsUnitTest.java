package com.metaxiii.fr;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;
import lombok.experimental.ExtensionMethod;
import org.junit.jupiter.api.Test;

@ExtensionMethod(ListUtils.class)
class ListUtilsUnitTest {

  @Test
  void givenDoubleList_whenUsingExtensionMethod_thenSum() {
    final List<Double> numbers = Arrays.asList(1.0, 2.0, 3.0);
    final int total = numbers.sum();
    assertEquals(6, total, "The sum of the list should be 6");
  }

  @Test
  void givenIntegerList_whenUsingExtensionMethod_thenSum() {
    final List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
    final int total = numbers.sum();
    assertEquals(15, total, "The sum of the list should be 15");
  }
}
