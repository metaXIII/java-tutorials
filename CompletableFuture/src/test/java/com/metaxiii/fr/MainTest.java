package com.metaxiii.fr;

import static org.awaitility.Awaitility.await;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

class MainTest {
  private Main main;

  @BeforeEach
  void init() {
    main = new Main();
  }

  @AfterEach
  void endEach() {
    main = null;
  }

  @Test
  void whenRunningCompletableFutureAsynchronously_thenGetMethodWaitsForResult()
    throws InterruptedException, ExecutionException {
    final var completableFuture = main.calculateAsync();
    final var result = completableFuture.get();
    assertEquals("Hello", result);
  }

  @Test
  void whenRunningCompletableFutureWithResult_thenGetMethodReturnsImmediately()
    throws InterruptedException, ExecutionException {
    final var completableFuture = CompletableFuture.completedFuture("Hello");
    final var result = completableFuture.get();
    assertEquals("Hello", result);
  }

  @Test
  void whenCreatingCompletableFutureWithSupplyAsync_thenFutureReturnsValue()
    throws ExecutionException, InterruptedException {
    final var future = CompletableFuture.supplyAsync(() -> "Hello");
    assertEquals("Hello", future.get());
  }

  @Test
  void whenAddingThenApplyToFuture_thenFunctionExecutesAfterComputationIsFinished()
    throws ExecutionException, InterruptedException {
    final var completableFuture = CompletableFuture.supplyAsync(
      () -> {
        await().pollDelay(5, TimeUnit.SECONDS).untilAsserted(() -> assertTrue(true));
        return "Hello";
      }
    );
    final var future = completableFuture.thenApply(s -> s + " World");
    assertEquals("Hello World", future.get());
  }

  @Test
  void whenAddingThenAcceptToFuture_thenFunctionExecutesAfterComputationIsFinished() {
    final var completableFuture = CompletableFuture.supplyAsync(() -> "Hello");
    final var future = completableFuture.thenAccept(s -> System.out.println("Computation returned: " + s));
    assertDoesNotThrow(() -> future.get());
  }

  @Test
  void whenAddingThenRunToFuture_thenFunctionExecutesAfterComputationIsFinished() {
    final var completableFuture = CompletableFuture.supplyAsync(() -> "Hello");
    final var future = completableFuture.thenRun(() -> System.out.println("Computation finished."));
    assertDoesNotThrow(() -> future.get());
  }

  @Test
  void whenUsingThenCompose_thenFuturesExecuteSequentially() throws ExecutionException, InterruptedException {
    final var completableFuture = CompletableFuture
      .supplyAsync(() -> "Hello")
      .thenCompose(s -> CompletableFuture.supplyAsync(() -> s + " World"));
    assertEquals("Hello World", completableFuture.get());
  }

  @Test
  void whenUsingThenCombine_thenWaitForExecutionOfBothFutures() throws ExecutionException, InterruptedException {
    final var completableFuture = CompletableFuture
      .supplyAsync(() -> "Hello")
      .thenCombine(CompletableFuture.supplyAsync(() -> " World"), (s1, s2) -> s1 + s2);
    assertEquals("Hello World", completableFuture.get());
  }

  @Test
  void whenUsingThenAcceptBoth_thenWaitForExecutionOfBothFutures() {
    assertDoesNotThrow(
      () ->
        CompletableFuture
          .supplyAsync(() -> "Hello")
          .thenAcceptBoth(CompletableFuture.supplyAsync(() -> " World"), (s1, s2) -> System.out.println(s1 + s2))
    );
  }

  @Test
  void whenPassingTransformation_thenFunctionExecutionWithThenApply() throws InterruptedException, ExecutionException {
    final var finalResult = main.compute().thenApply(s -> s + 1);
    assertEquals(11, finalResult.get());
  }

  @Test
  void whenPassingPreviousStage_thenFunctionExecutionWithThenCompose() throws InterruptedException, ExecutionException {
    CompletableFuture<Integer> finalResult = main.compute().thenCompose(integer -> main.computeAnother(integer));
    assertEquals(20, (int) finalResult.get());
  }

  @Test
  void whenFutureCombinedWithAllOfCompletes_thenAllFuturesAreDone() throws ExecutionException, InterruptedException {
    final var future1 = CompletableFuture.supplyAsync(() -> "Hello");
    final var future2 = CompletableFuture.supplyAsync(() -> "Beautiful");
    final var future3 = CompletableFuture.supplyAsync(() -> "World");
    final var combinedFuture = CompletableFuture.allOf(future1, future2, future3);
    combinedFuture.get();
    assertTrue(future1.isDone());
    assertTrue(future2.isDone());
    assertTrue(future3.isDone());
    final var combined = Stream
      .of(future1, future2, future3)
      .map(CompletableFuture::join)
      .collect(Collectors.joining(" "));
    assertEquals("Hello Beautiful World", combined);
  }

  @ParameterizedTest
  @ValueSource(strings = "you")
  @NullSource
  void whenFutureThrows_thenHandleMethodReceivesException(final String name)
    throws ExecutionException, InterruptedException {
    CompletableFuture<String> completableFuture = CompletableFuture
      .supplyAsync(
        () -> {
          if (name == null) {
            throw new RuntimeException("Computation error!");
          }
          return "Hello, " + name;
        }
      )
      .handle((s, t) -> s != null ? s : "Hello, Stranger!");
    if (name == null) {
      assertEquals("Hello, Stranger!", completableFuture.get());
    } else {
      assertEquals("Hello, you", completableFuture.get());
    }
  }

  @Test
  void whenAddingThenApplyAsyncToFuture_thenFunctionExecutesAfterComputationIsFinished()
    throws ExecutionException, InterruptedException {
    final var completableFuture = CompletableFuture.supplyAsync(() -> "Hello");
    final var future = completableFuture.thenApplyAsync(s -> s + " World");
    assertEquals("Hello World", future.get());
  }
}
