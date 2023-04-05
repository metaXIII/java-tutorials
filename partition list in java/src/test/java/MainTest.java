import static org.junit.jupiter.api.Assertions.assertEquals;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import org.apache.commons.collections4.ListUtils;
import org.junit.jupiter.api.Test;

class MainTest {

  @Test
  void givenList_whenParitioningIntoNSublists_thenCorrect() {
    List<Integer> intList = Lists.newArrayList(1, 2, 3, 4, 5, 6, 7, 8);
    List<List<Integer>> subSets = Lists.partition(intList, 3);
    List<Integer> lastPartition = subSets.get(2);
    List<Integer> expectedLastPartition = Lists.<Integer>newArrayList(7, 8);
    assertEquals(3, subSets.size());
    assertEquals(expectedLastPartition, lastPartition);
  }

  @Test
  void givenCollection_whenParitioningIntoNSublists_thenCorrect() {
    Collection<Integer> intCollection = Lists.newArrayList(1, 2, 3, 4, 5, 6, 7, 8);
    Iterable<List<Integer>> subSets = Iterables.partition(intCollection, 3);
    List<Integer> firstPartition = subSets.iterator().next();
    List<Integer> expectedLastPartition = Lists.<Integer>newArrayList(1, 2, 3);
    assertEquals(expectedLastPartition, firstPartition);
  }

  @Test
  void givenListPartitioned_whenOriginalListIsModified_thenPartitionsChangeAsWell() {
    List<Integer> intList = Lists.newArrayList(1, 2, 3, 4, 5, 6, 7, 8);
    List<List<Integer>> subSets = Lists.partition(intList, 3);
    intList.add(9);
    List<Integer> lastPartition = subSets.get(2);
    List<Integer> expectedLastPartition = Lists.<Integer>newArrayList(7, 8, 9);
    assertEquals(expectedLastPartition, lastPartition);
  }

  @Test
  void givenList_whenParitioningIntoNSublists_thenCorrectWithApacheCollections() {
    List<Integer> intList = Lists.newArrayList(1, 2, 3, 4, 5, 6, 7, 8);
    List<List<Integer>> subSets = ListUtils.partition(intList, 3);
    List<Integer> lastPartition = subSets.get(2);
    List<Integer> expectedLastPartition = Lists.<Integer>newArrayList(7, 8);
    assertEquals(3, subSets.size());
    assertEquals(expectedLastPartition, lastPartition);
  }

  @Test
  void givenList_whenParitioningIntoSublistsUsingPartitionBy_thenCorrect() {
    List<Integer> intList = Lists.newArrayList(1, 2, 3, 4, 5, 6, 7, 8);
    Map<Boolean, List<Integer>> groups = intList.stream().collect(Collectors.partitioningBy(s -> s > 6));
    List<List<Integer>> subSets = new ArrayList<List<Integer>>(groups.values());
    List<Integer> lastPartition = subSets.get(1);
    List<Integer> expectedLastPartition = Lists.<Integer>newArrayList(7, 8);
    assertEquals(2, subSets.size());
    assertEquals(expectedLastPartition, lastPartition);
  }

  @Test
  public final void givenList_whenParitioningIntoNSublistsUsingGroupingBy_thenCorrect() {
    List<Integer> intList = Lists.newArrayList(1, 2, 3, 4, 5, 6, 7, 8);
    Map<Integer, List<Integer>> groups = intList.stream().collect(Collectors.groupingBy(s -> (s - 1) / 3));
    List<List<Integer>> subSets = new ArrayList<List<Integer>>(groups.values());
    List<Integer> lastPartition = subSets.get(2);
    List<Integer> expectedLastPartition = Lists.<Integer>newArrayList(7, 8);
    assertEquals(3, subSets.size());
    assertEquals(expectedLastPartition, lastPartition);
  }

  @Test
  void givenList_whenSplittingBySeparator_thenCorrect() {
    List<Integer> intList = Lists.newArrayList(1, 2, 3, 0, 4, 5, 6, 0, 7, 8);
    int[] indexes = Stream
      .of(
        IntStream.of(-1),
        IntStream.range(0, intList.size()).filter(i -> intList.get(i) == 0),
        IntStream.of(intList.size())
      )
      .flatMapToInt(s -> s)
      .toArray();
    List<List<Integer>> subSets = IntStream
      .range(0, indexes.length - 1)
      .mapToObj(i -> intList.subList(indexes[i] + 1, indexes[i + 1]))
      .collect(Collectors.toList());
    List<Integer> lastPartition = subSets.get(2);
    List<Integer> expectedLastPartition = Lists.<Integer>newArrayList(7, 8);
    assertEquals(3, subSets.size());
    assertEquals(expectedLastPartition, lastPartition);
  }
}
