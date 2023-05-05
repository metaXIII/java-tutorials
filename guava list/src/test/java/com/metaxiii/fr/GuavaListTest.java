package com.metaxiii.fr;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import java.util.List;
import java.util.Objects;
import org.junit.jupiter.api.Test;

class GuavaListTest {

  @Test
  void whenReverseList_thenReversed() {
    final var names = Lists.newArrayList("John", "Adam", "Jane");
    final var reversed = Lists.reverse(names);
    assertTrue(reversed.containsAll(List.of("Jane", "Adam", "John")));
  }

  @Test
  void whenCreateCharacterListFromString_thenCreated() {
    final var chars = Lists.charactersOf("John");
    assertEquals(4, chars.size());
    assertTrue(chars.containsAll(List.of('J', 'o', 'h', 'n')));
  }

  @Test
  void whenPartitionList_thenPartitioned() {
    final var names = Lists.newArrayList("John", "Jane", "Adam", "Tom", "Viki", "Tyler");
    final var result = Lists.partition(names, 2);
    assertEquals(3, result.size());
    assertTrue(result.get(0).containsAll(List.of("John", "Jane")));
    assertTrue(result.get(1).containsAll(List.of("Adam", "Tom")));
    assertTrue(result.get(2).containsAll(List.of("Viki", "Tyler")));
  }

  @Test
  void whenRemoveDuplicatesFromList_thenRemoved() {
    final var chars = Lists.newArrayList('h', 'e', 'l', 'l', 'o');
    assertEquals(5, chars.size());
    final var result = ImmutableSet.copyOf(chars).asList();
    assertTrue(result.containsAll(List.of('h', 'e', 'l', 'o')));
  }

  @Test
  void whenRemoveNullFromList_thenRemoved() {
    final var names = Lists.newArrayList("John", null, "Adam", null, "Jane");
    Iterables.removeIf(names, Objects::isNull);
    assertEquals(3, names.size());
    assertTrue(names.containsAll(List.of("John", "Adam", "Jane")));
  }

  @Test
  void whenCreateImmutableList_thenCreated() {
    final var names = Lists.newArrayList("John", "Adam", "Jane");
    names.add("Tom");
    assertEquals(4, names.size());
    final var immutable = ImmutableList.copyOf(names);
    assertTrue(immutable.containsAll(List.of("John", "Adam", "Jane", "Tom")));
  }
}
