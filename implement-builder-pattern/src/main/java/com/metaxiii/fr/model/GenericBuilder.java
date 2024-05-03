package com.metaxiii.fr.model;

import java.util.function.BiConsumer;
import java.util.function.Supplier;

public class GenericBuilder<T> {

  private final Supplier<T> supplier;

  public GenericBuilder(final Supplier<T> supplier) {
    this.supplier = supplier;
  }

  public static <T> GenericBuilder<T> of(final Supplier<T> supplier) {
    return new GenericBuilder<>(supplier);
  }

  public T build() {
    return supplier.get();
  }

  public <P> GenericBuilder<T> with(final BiConsumer<T, P> consumer, final P value) {
    return new GenericBuilder<>(() -> {
      final T obj = supplier.get();
      consumer.accept(obj, value);
      return obj;
    });
  }
}
