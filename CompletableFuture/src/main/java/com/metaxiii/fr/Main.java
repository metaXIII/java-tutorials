package com.metaxiii.fr;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Main {

  @SuppressWarnings("resource")
  public Future<String> calculateAsync() {
    final CompletableFuture<String> completableFuture = new CompletableFuture<>();
    Executors
      .newCachedThreadPool()
      .submit(() -> {
        Thread.sleep(500);
        completableFuture.complete("Hello");
        return null;
      });
    return completableFuture;
  }

  public CompletableFuture<Integer> compute() {
    return CompletableFuture.supplyAsync(() -> 10);
  }

  public CompletableFuture<Integer> computeAnother(final int i) {
    return CompletableFuture.supplyAsync(() -> 10 + i);
  }
}
