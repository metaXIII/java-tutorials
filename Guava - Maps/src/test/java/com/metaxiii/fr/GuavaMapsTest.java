package com.metaxiii.fr;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.google.common.base.Function;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.HashBasedTable;
import com.google.common.collect.HashBiMap;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSortedMap;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimaps;
import com.google.common.collect.MutableClassToInstanceMap;
import com.google.common.collect.Ordering;
import com.google.common.collect.Tables;
import java.util.List;
import java.util.Objects;
import org.junit.jupiter.api.Test;

class GuavaMapsTest {

  @Test
  void whenCreateBiMap_thenCreated() {
    final var words = HashBiMap.create();
    words.put("First", 1);
    words.put("Second", 2);
    words.put("Third", 3);
    assertEquals(2, words.get("Second"));
    assertEquals("Third", words.inverse().get(3));
  }

  @Test
  void whenCreateMultimap_thenCreated() {
    final var multimap = ArrayListMultimap.create();
    multimap.put("fruit", "apple");
    multimap.put("fruit", "banana");
    multimap.put("pet", "cat");
    multimap.put("pet", "dog");
    assertTrue(multimap.get("fruit").containsAll(List.of("apple", "banana")));
    assertTrue(multimap.get("pet").containsAll(List.of("cat", "dog")));
  }

  @Test
  void whenCreatingClassToInstanceMap_thenCorrect() {
    final var numbers = MutableClassToInstanceMap.create();
    numbers.putInstance(Integer.class, 1);
    numbers.putInstance(Double.class, 1.5);
    assertEquals(1, numbers.get(Integer.class));
    assertEquals(1.5, numbers.get(Double.class));
  }

  @Test
  void whenCreatingImmutableMap_thenCorrect() {
    final var salary = ImmutableMap
      .<String, Integer>builder()
      .put("John", 1000)
      .put("Jane", 1500)
      .put("Adam", 2000)
      .put("Tom", 2000)
      .build();

    assertEquals(1000, salary.get("John"));
    assertEquals(2000, salary.get("Tom"));
  }

  @Test
  void whenCreatingTable_thenCorrect() {
    final var distance = HashBasedTable.create();
    distance.put("London", "Paris", 340);
    distance.put("New York", "Los Angeles", 3940);
    distance.put("London", "New York", 5576);
    assertEquals(3940, distance.get("New York", "Los Angeles"));
    assertTrue(distance.columnKeySet().containsAll(List.of("Paris", "New York", "Los Angeles")));
    assertTrue(distance.rowKeySet().containsAll(List.of("London", "New York")));
  }

  @Test
  void whenGroupingListsUsingMultimap_thenGrouped() {
    final var names = Lists.newArrayList("John", "Adam", "Tom");
    final var func = new Function<String, Integer>() {

      public Integer apply(final String input) {
        return input.length();
      }
    };
    final var groups = Multimaps.index(names, func);
    assertTrue(groups.get(3).contains("Tom"));
    assertTrue(groups.get(4).containsAll(List.of("John", "Adam")));
  }

  @Test
  void whenTransposingTable_thenCorrect() {
    final var distance = HashBasedTable.create();
    distance.put("London", "Paris", 340);
    distance.put("New York", "Los Angeles", 3940);
    distance.put("London", "New York", 5576);
    final var transposed = Tables.transpose(distance);
    assertTrue(transposed.rowKeySet().containsAll(List.of("Paris", "New York", "Los Angeles")));
    assertTrue(transposed.columnKeySet().containsAll(List.of("London", "New York")));
  }

  @Test
  void whenUsingSortedMap_thenKeysAreSorted() {
    final var salary = new ImmutableSortedMap.Builder<String, Integer>(Ordering.natural())
      .put("John", 1000)
      .put("Jane", 1500)
      .put("Adam", 2000)
      .put("Tom", 2000)
      .build();
    assertEquals("Adam", salary.firstKey());
    assertEquals(2000, Objects.requireNonNull(salary.lastEntry()).getValue().intValue());
  }
}
