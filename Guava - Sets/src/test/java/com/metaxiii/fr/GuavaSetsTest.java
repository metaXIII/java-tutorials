package com.metaxiii.fr;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.google.common.base.Joiner;
import com.google.common.collect.ContiguousSet;
import com.google.common.collect.DiscreteDomain;
import com.google.common.collect.HashMultiset;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;
import com.google.common.collect.Multiset;
import com.google.common.collect.Multisets;
import com.google.common.collect.Range;
import com.google.common.collect.Sets;
import com.google.common.collect.TreeRangeSet;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Test;

class GuavaSetsTest {

  @Test
  void whenCalculatingCartesianProductOfSets_thenCorrect() {
    final var first = ImmutableSet.of('a', 'b');
    final var second = ImmutableSet.of('c', 'd');
    final var result = Sets.cartesianProduct(ImmutableList.of(first, second));

    final Function<List<Character>, String> func = input -> Joiner.on(" ").join(input);
    final Iterable<String> joined = result.stream().map(func).collect(Collectors.toList());
    assertTrue(Sets.newHashSet(joined).containsAll(List.of("a c", "a d", "b c", "b d")));
  }

  @Test
  void whenCalculatingPowerSet_thenCorrect() {
    final var chars = ImmutableSet.of('a', 'b');
    final var result = Sets.powerSet(chars);
    final var empty = ImmutableSet.<Character>builder().build();
    final var a = ImmutableSet.of('a');
    final var b = ImmutableSet.of('b');
    final var aB = ImmutableSet.of('a', 'b');
    assertTrue(result.containsAll(List.of(empty, a, b, aB)));
  }

  @Test
  void whenCalculatingSetIntersection_thenCorrect() {
    final var first = ImmutableSet.of('a', 'b', 'c');
    final var second = ImmutableSet.of('b', 'c', 'd');
    final var intersection = Sets.intersection(first, second);
    assertTrue(intersection.containsAll(List.of('b', 'c')));
  }

  @Test
  void whenCalculatingSetSymmetricDifference_thenCorrect() {
    final var first = ImmutableSet.of('a', 'b', 'c');
    final var second = ImmutableSet.of('b', 'c', 'd');
    final var intersection = Sets.symmetricDifference(first, second);
    assertTrue(intersection.containsAll(List.of('a', 'd')));
  }

  @Test
  void whenCalculatingUnionOfSets_thenCorrect() {
    final var first = ImmutableSet.of('a', 'b', 'c');
    final var second = ImmutableSet.of('b', 'c', 'd');
    final var union = Sets.union(first, second);
    assertTrue(union.containsAll(List.of('a', 'b', 'c', 'd')));
  }

  @Test
  void whenCreatingRangeOfIntegersSet_thenCreated() {
    final int start = 10;
    final int end = 30;
    final var set = ContiguousSet.create(Range.closed(start, end), DiscreteDomain.integers());
    assertEquals(21, set.size());
    assertEquals(10, set.first().intValue());
    assertEquals(30, set.last().intValue());
  }

  @SuppressWarnings("UnstableApiUsage")
  @Test
  void whenGetTopOccurringElementsWithMultiSet_thenCorrect() {
    final Multiset<String> names = HashMultiset.create();
    names.add("John");
    names.add("Adam", 5);
    names.add("Jane");
    names.add("Tom", 2);
    final var sorted = Multisets.copyHighestCountFirst(names).elementSet();
    final List<String> sortedAsList = Lists.newArrayList(sorted);
    assertEquals("Adam", sortedAsList.get(0));
    assertEquals("Tom", sortedAsList.get(1));
  }

  @Test
  void whenInsertDuplicatesInMultiSet_thenInserted() {
    final Multiset<String> names = HashMultiset.create();
    names.add("John");
    names.add("Adam", 3);
    names.add("John");

    assertEquals(2, names.count("John"));
    names.remove("John");
    assertEquals(1, names.count("John"));

    assertEquals(3, names.count("Adam"));
    names.remove("Adam", 2);
    assertEquals(1, names.count("Adam"));
  }

  @SuppressWarnings("UnstableApiUsage")
  @Test
  void whenUsingRangeSet_thenCorrect() {
    final var rangeSet = TreeRangeSet.create();
    rangeSet.add(Range.closed(1, 10));
    rangeSet.add(Range.closed(12, 15));
    assertEquals(2, rangeSet.asRanges().size());

    rangeSet.add(Range.closed(10, 12));
    assertTrue(rangeSet.encloses(Range.closed(1, 15)));
    assertEquals(1, rangeSet.asRanges().size());
  }
}
