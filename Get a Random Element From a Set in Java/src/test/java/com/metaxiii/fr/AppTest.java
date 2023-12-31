package com.metaxiii.fr;

import static com.metaxiii.fr.App.getByRandomClassSafe;
import static com.metaxiii.fr.App.getByRandomClassUnsafe;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Test;

class AppTest {

  @Test
  void testException() {
    final var set = Set.of();
    assertThrows(IllegalArgumentException.class, () -> getByRandomClassSafe(null));
    assertThrows(IllegalArgumentException.class, () -> getByRandomClassSafe(set));
    assertThrows(IllegalArgumentException.class, () -> getByRandomClassUnsafe(null));
    assertThrows(IllegalArgumentException.class, () -> getByRandomClassUnsafe(set));
  }

  @Test
  void testSafe() {
    final Set<String> animals = new HashSet<>();
    animals.add("Lion");
    animals.add("Elephant");
    animals.add("Giraffe");
    final String randomAnimal = getByRandomClassSafe(animals);
    assertNotNull(randomAnimal);
    System.out.println("Randomly picked animal: " + randomAnimal);
  }

  @Test
  void testUnsafe() {
    final Set<String> animals = new HashSet<>();
    animals.add("Lion");
    animals.add("Elephant");
    animals.add("Giraffe");
    final String randomAnimal = getByRandomClassUnsafe(animals);
    assertNotNull(randomAnimal);
    System.out.println("Randomly picked animal: " + randomAnimal);
  }
}
