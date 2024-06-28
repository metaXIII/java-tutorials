package com.metaxiii.fr;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class PassingMethodParameterWithInterfaceBeforeJava8Test {

  interface Operation {
    int execute(int a, int b);
  }

  int performOperation(final int a, final int b, final Operation operation) {
    return operation.execute(a, b);
  }

  @Test
  @SuppressWarnings("Convert2Lambda")
  void itShouldPassMethodParameterWithInterface_BeforeJava8() {
    final var actualResult = performOperation(
      5,
      3,
      new Operation() {
        @Override
        public int execute(final int a, final int b) {
          return a + b;
        }
      }
    );
    assertEquals(8, actualResult);
  }
}
