package com.metaxiii.fr.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Test;

class HumanTest {

  @Test
  public void givenPreLambda_whenSortingEntitiesByName_thenCorrectlySorted() {
    final ArrayList<Human> humans = new ArrayList<>(List.of(new Human("Sarah", 10), new Human("Jack", 12)));
    Collections.sort(
      humans,
      new Comparator<>() {
        @Override
        public int compare(Human h1, Human h2) {
          return h1.getName().compareTo(h2.getName());
        }
      }
    );
    assertEquals(humans.get(0), new Human("Jack", 12));
  }

  @Test
  public void whenSortingEntitiesByName_thenCorrectlySorted() {
    List<Human> humans = new ArrayList<>(List.of(new Human("Sarah", 10), new Human("Jack", 12)));
    humans.sort((Human h1, Human h2) -> h1.getName().compareTo(h2.getName()));
    assertEquals(humans.get(0), new Human("Jack", 12));
  }

  @Test
  public void givenLambdaShortForm_whenSortingEntitiesByName_thenCorrectlySorted() {
    List<Human> humans = new ArrayList<>(List.of(new Human("Sarah", 10), new Human("Jack", 12)));
    humans.sort((h1, h2) -> h1.getName().compareTo(h2.getName()));
    assertEquals(humans.get(0), new Human("Jack", 12));
  }

  @Test
  public void givenMethodDefinition_whenSortingEntitiesByNameThenAge_thenCorrectlySorted() {
    List<Human> humans = new ArrayList<>(List.of(new Human("Sarah", 10), new Human("Jack", 12)));
    humans.sort(Human::compareByNameThenAge);
    assertEquals(humans.get(0), new Human("Jack", 12));
  }

  @Test
  public void givenInstanceMethod_whenSortingEntitiesByName_thenCorrectlySorted() {
    List<Human> humans = new ArrayList<>(List.of(new Human("Sarah", 10), new Human("Jack", 12)));
    Collections.sort(humans, Comparator.comparing(Human::getName));
    assertEquals(humans.get(0), new Human("Jack", 12));
  }

  @Test
  public void whenSortingEntitiesByNameReversed_thenCorrectlySorted() {
    List<Human> humans = new ArrayList<>(List.of(new Human("Sarah", 10), new Human("Jack", 12)));
    Comparator<Human> comparator = (h1, h2) -> h1.getName().compareTo(h2.getName());
    humans.sort(comparator.reversed());
    assertEquals(humans.get(0), new Human("Sarah", 10));
  }

  @Test
  public void whenSortingEntitiesByNameThenAge_thenCorrectlySorted() {
    List<Human> humans = new ArrayList<>(
      List.of(new Human("Sarah", 10), new Human("Sarah", 10), new Human("Zack", 12))
    );
    humans.sort((lhs, rhs) -> {
      if (lhs.getName().equals(rhs.getName())) {
        return Integer.compare(lhs.getAge(), rhs.getAge());
      } else {
        return lhs.getName().compareTo(rhs.getName());
      }
    });
    assertEquals(humans.get(0), new Human("Sarah", 10));
  }

  @Test
  public void givenComposition_whenSortingEntitiesByNameThenAge_thenCorrectlySorted() {
    List<Human> humans = new ArrayList<>(
      List.of(new Human("Sarah", 10), new Human("Sarah", 12), new Human("Zack", 12))
    );
    humans.sort(Comparator.comparing(Human::getName).thenComparing(Human::getAge));
    assertEquals(humans.get(0), new Human("Sarah", 10));
  }

  @Test
  public final void givenStreamNaturalOrdering_whenSortingEntitiesByName_thenCorrectlySorted() {
    List<String> letters = List.of("B", "A", "C");
    List<String> sortedLetters = letters.stream().sorted().collect(Collectors.toList());
    assertEquals("A", sortedLetters.get(0));
  }

  @Test
  public final void givenStreamCustomOrdering_whenSortingEntitiesByName_thenCorrectlySorted() {
    List<Human> humans = new ArrayList<>(List.of(new Human("Sarah", 10), new Human("Jack", 12)));
    Comparator<Human> nameComparator = (h1, h2) -> h1.getName().compareTo(h2.getName());
    List<Human> sortedHumans = humans.stream().sorted(nameComparator).collect(Collectors.toList());
    assertEquals(sortedHumans.get(0), new Human("Jack", 12));
  }

  @Test
  public final void givenStreamComparatorOrdering_whenSortingEntitiesByName_thenCorrectlySorted() {
    List<Human> humans = new ArrayList<>(List.of(new Human("Sarah", 10), new Human("Jack", 12)));
    List<Human> sortedHumans = humans
      .stream()
      .sorted(Comparator.comparing(Human::getName))
      .collect(Collectors.toList());
    assertEquals(sortedHumans.get(0), new Human("Jack", 12));
  }

  @Test
  public final void givenStreamNaturalOrdering_whenSortingEntitiesByNameReversed_thenCorrectlySorted() {
    List<String> letters = List.of("B", "A", "C");
    List<String> reverseSortedLetters = letters.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
    assertEquals("C", reverseSortedLetters.get(0));
  }

  @Test
  public final void givenStreamCustomOrdering_whenSortingEntitiesByNameReversed_thenCorrectlySorted() {
    List<Human> humans = new ArrayList<>(List.of(new Human("Sarah", 10), new Human("Jack", 12)));
    Comparator<Human> reverseNameComparator = (h1, h2) -> h2.getName().compareTo(h1.getName());
    List<Human> reverseSortedHumans = humans.stream().sorted(reverseNameComparator).collect(Collectors.toList());
    assertEquals(reverseSortedHumans.get(0), new Human("Sarah", 10));
  }

  @Test
  public final void givenStreamComparatorOrdering_whenSortingEntitiesByNameReversed_thenCorrectlySorted() {
    List<Human> humans = new ArrayList<>(List.of(new Human("Sarah", 10), new Human("Jack", 12)));
    List<Human> reverseSortedHumans = humans
      .stream()
      .sorted(Comparator.comparing(Human::getName, Comparator.reverseOrder()))
      .collect(Collectors.toList());
    assertEquals(reverseSortedHumans.get(0), new Human("Sarah", 10));
  }

  @Test
  public void givenANullElement_whenSortingEntitiesByName_thenThrowsNPE() {
    final var humans = new ArrayList<Human>();
    humans.add(null);
    humans.add(new Human("Jack", 12));
    assertThrows(
      NullPointerException.class,
      () -> {
        humans.sort((h1, h2) -> h1.getName().compareTo(h2.getName()));
      }
    );
  }

  @Test
  public void givenANullElement_whenSortingEntitiesByNameManually_thenMovesTheNullToLast() {
    final var humans = new ArrayList<Human>();
    humans.add(null);
    humans.add(new Human("Jack", 12));
    humans.add(null);
    humans.sort((h1, h2) -> {
      if (h1 == null) {
        return h2 == null ? 0 : 1;
      } else if (h2 == null) {
        return -1;
      }
      return h1.getName().compareTo(h2.getName());
    });
    assertNotNull(humans.get(0));
    assertNull(humans.get(1));
    assertNull(humans.get(2));
  }

  @Test
  public void givenANullElement_whenSortingEntitiesByName_thenMovesTheNullToLast() {
    final var humans = new ArrayList<Human>();
    humans.add(null);
    humans.add(new Human("Jack", 12));
    humans.add(null);
    humans.sort(Comparator.nullsLast(Comparator.comparing(Human::getName)));
    assertNotNull(humans.get(0));
    assertNull(humans.get(1));
    assertNull(humans.get(2));
  }

  @Test
  public void givenANullElement_whenSortingEntitiesByName_thenMovesTheNullToStart() {
    final var humans = new ArrayList<Human>();
    humans.add(null);
    humans.add(new Human("Jack", 12));
    humans.add(null);
    humans.sort(Comparator.nullsFirst(Comparator.comparing(Human::getName)));
    assertNull(humans.get(0));
    assertNull(humans.get(1));
    assertNotNull(humans.get(2));
  }
}
