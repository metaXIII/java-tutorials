package com.metaxiii.fr;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

public class Main {

  public Future<String> calculateAsync() {
    return CompletableFuture.supplyAsync(() -> {
      try {
        Thread.sleep(500); // Simuler une tâche longue
        return "Hello";
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
        return null;
      }
    });
  }

  public CompletableFuture<Integer> compute() {
    return CompletableFuture.supplyAsync(() -> 10);
  }

  public CompletableFuture<Integer> computeAnother(final int i) {
    return CompletableFuture.supplyAsync(() -> 10 + i);
  }
}
