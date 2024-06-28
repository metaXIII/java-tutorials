package com.metaxiii.fr;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.google.common.collect.Iterables;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import org.apache.commons.collections4.CollectionUtils;
import org.junit.jupiter.api.Test;

class AppTest {

  @Test
  void givenArrayList_whenUsingAnyMatch_thenAvoidDuplicates() {
    final var distinctCities = Arrays.asList("Tamassint", "Madrid", "Paris", "Tokyo");
    final var arrayListCities = new ArrayList<>(distinctCities);
    final var newCity = "Tamassint";
    final var isCityPresent = arrayListCities.stream().anyMatch(city -> city.equals(newCity));
    if (!isCityPresent) {
      arrayListCities.add(newCity);
    }
    assertEquals(arrayListCities.size(), distinctCities.size());
  }

  @Test
  void givenArrayList_whenUsingCollectionUtilsContainsAny_thenAvoidDuplicates() {
    final var distinctCities = Arrays.asList("Tamassint", "Madrid", "Paris", "Tokyo");
    final var arrayListCities = new ArrayList<>(distinctCities);
    final var newCity = "Tokyo";
    final var isCityPresent = CollectionUtils.containsAny(arrayListCities, newCity);
    if (!isCityPresent) {
      arrayListCities.add(newCity);
    }
    assertEquals(arrayListCities.size(), distinctCities.size());
  }

  @Test
  void givenArrayList_whenUsingContains_thenAvoidDuplicates() {
    final var distinctCities = Arrays.asList("Tamassint", "Madrid", "Paris", "Tokyo");
    final var arrayListCities = new ArrayList<>(distinctCities);
    final var newCity = "Madrid";
    if (!arrayListCities.contains(newCity)) {
      arrayListCities.add(newCity);
    }
    assertEquals(arrayListCities.size(), distinctCities.size());
  }

  @Test
  void givenArrayList_whenUsingFilterAndFindFirst_thenAvoidDuplicates() {
    final var distinctCities = Arrays.asList("Tamassint", "Madrid", "Paris", "Tokyo");
    final var arrayListCities = new ArrayList<>(distinctCities);
    final var newCity = "Tamassint";
    final var optionalCity = arrayListCities.stream().filter(city -> city.equals(newCity)).findFirst();
    if (optionalCity.isEmpty()) {
      arrayListCities.add(newCity);
    }
    assertEquals(arrayListCities.size(), distinctCities.size());
  }

  @Test
  void givenArrayList_whenUsingIterablesContains_thenAvoidDuplicates() {
    final var distinctCities = Arrays.asList("Tamassint", "Madrid", "Paris", "Tokyo");
    final var arrayListCities = new ArrayList<>(distinctCities);
    final var newCity = "Paris";
    final var isCityPresent = Iterables.contains(arrayListCities, newCity);
    if (!isCityPresent) {
      arrayListCities.add(newCity);
    }
    assertEquals(arrayListCities.size(), distinctCities.size());
  }

  @Test
  void givenArrayList_whenUsingSet_thenAvoidDuplicates() {
    final Set<String> distinctCities = new HashSet<>(Arrays.asList("Tamassint", "Madrid", "Paris", "Tokyo"));
    final var newCity = "Paris";
    distinctCities.add(newCity);
    final var arrayListCities = new ArrayList<>(distinctCities);
    assertEquals(arrayListCities.size(), distinctCities.size());
  }
}
