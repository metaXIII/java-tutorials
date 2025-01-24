package com.metaxiii.fr;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class AppTest {

  @Test
  void givenMemorySize_whenComparingFloatAndDouble_thenDoubleRequiresMoreMemory() {
    assertEquals(4, Float.BYTES, "Float should occupy 4 bytes of memory");
    assertEquals(8, Double.BYTES, "Double should occupy 8 bytes of memory");
  }

  @Test
  void givenPrecisionLimits_whenExceedingPrecision_thenFloatTruncatesAndDoubleMaintains() {
    float floatValue = 1.123456789f;
    assertEquals(1.1234568f, floatValue, "Float should truncate beyond 7 digits");

    double doubleValue = 1.1234567891234566d; // Exceeds 15 digits of precision for double
    assertEquals(1.123456789123457, doubleValue, 1e-15, "Double should round beyond 15 digits");
  }

  @Test
  @SuppressWarnings("ConstantValue")
  void givenRangeLimits_whenExceedingBounds_thenFloatUnderflowsAndDoubleHandles() {
    float largeFloat = 3.4e38f;
    assertTrue(largeFloat > 0, "Float should handle large positive values");

    float smallFloat = 1.4e-45f;
    assertTrue(smallFloat > 0, "Float should handle very small positive values");

    double largeDouble = 1.7e308;
    assertTrue(largeDouble > 0, "Double should handle extremely large values");

    double smallDouble = 4.9e-324;
    assertTrue(smallDouble > 0, "Double should handle extremely small positive values");
  }

  @Test
  void givenUnderflowScenario_whenExceedingFloatRange_thenFloatUnderflowsToZero() {
    float underflowValue = 1.4e-45f / 2; // Smaller than the smallest normalized float value
    assertEquals(
      0.0f,
      underflowValue,
      "Float should underflow to zero for values smaller than the smallest representable number"
    );
  }
}
