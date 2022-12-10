package com.metaxiii.fr.conversion;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.metaxiii.fr.model.Car;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class HandlingDateFormatTest {

  private HandlingDateFormat handlingDateFormat;
  private static final String JSON =
    "{\"car\":{\"color\":\"Black\",\"type\":\"BMW\"},\"datePurcharsed\":\"";

  @BeforeEach
  public void init() {
    this.handlingDateFormat = new HandlingDateFormat();
  }

  @AfterEach
  public void endEach() {
    this.handlingDateFormat = null;
  }

  @Test
  void process() {
    assertDoesNotThrow(() -> {
      final Car car = new Car("Black", "BMW");
      assertTrue(handlingDateFormat.process(car).contains(JSON));
    });
  }
}
