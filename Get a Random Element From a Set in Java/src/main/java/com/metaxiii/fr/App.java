package com.metaxiii.fr;

import java.security.SecureRandom;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class App {

  private static final Random RANDOM = new SecureRandom();

  @SuppressWarnings("all")
  private static final ThreadLocalRandom CURRENT = ThreadLocalRandom.current();

  public static <T> T getByRandomClassUnsafe(final Set<T> set) {
    if (set == null || set.isEmpty()) {
      throw new IllegalArgumentException("The Set cannot be empty.");
    }
    final int randomIndex = RANDOM.nextInt(set.size());
    int i = 0;
    for (final T element : set) {
      if (i == randomIndex) {
        return element;
      }
      i++;
    }
    throw new IllegalStateException("Something went wrong while picking a random element.");
  }

  public static <T> T getByRandomClassSafe(final Set<T> set) {
    if (set == null || set.isEmpty()) {
      throw new IllegalArgumentException("The Set cannot be empty.");
    }
    final var randomInt = CURRENT.nextInt(set.size());
    int i = 0;
    for (final T element : set) {
      if (i == randomInt) {
        return element;
      }
      i++;
    }
    throw new IllegalStateException("Something went wrong while picking a random element.");
  }
}
