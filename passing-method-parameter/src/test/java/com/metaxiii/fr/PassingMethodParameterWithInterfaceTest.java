package com.metaxiii.fr;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.concurrent.Callable;
import java.util.function.IntBinaryOperator;
import org.junit.jupiter.api.Test;

class PassingMethodParameterWithInterfaceTest {

  @Test
  void itShouldPassMethodParameterWithCallable() throws Exception {
    final Callable<Integer> task = () -> 5 + 3;
    final int actualResult = executeCallable(task);
    assertEquals(8, actualResult);
  }

  int executeCallable(final Callable<Integer> task) throws Exception {
    return task.call();
  }

  @Test
  @SuppressWarnings("Convert2MethodRef")
  void itShouldPassMethodParameterWithFunction() {
    final int actualResult = executeBiFunction((a, b) -> a + b, 5, 3);
    assertEquals(8, actualResult);
  }

  int executeBiFunction(
      final IntBinaryOperator function,
      final int a,
      final int b) {
    return function.applyAsInt(a, b);
  }

  @Test
  void itShouldPassMethodParameterWithFunctionReference() {
    final int actualResult = executeBiFunction(Integer::sum, 2, 4);
    assertEquals(6, actualResult);
  }

  @Test
  @SuppressWarnings("Convert2MethodRef")
  void itShouldPassMethodParameterWithInterface() {
    final var actualResult = performOperation(5, 3, (a, b) -> a + b);
    assertEquals(8, actualResult);
  }

  int performOperation(final int a, final int b, final Operation operation) {
    return operation.execute(a, b);
  }

  @Test
  void itShouldPassMethodParameterWithMethodReference() {
    final var actualResult = performOperation(2, 2, Integer::sum);
    assertEquals(4, actualResult);
  }

  @FunctionalInterface
  interface Operation {
    int execute(int a, int b);
  }
}
