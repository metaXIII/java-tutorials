package com.metaxiii.fr;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.Comparator;
import org.junit.jupiter.api.Test;

class AppTest {

  private static final String[] INPUT_ARRAY = new String[] { "am", "today", "too", "I", "busy" };
  private static final String[] SORTED = new String[] { "I", "am", "too", "busy", "today" };

  @Test
  void whenSortByCustomComparator_thenArraySorted() {
    final StringLengthComparator comparator = new StringLengthComparator();
    Arrays.sort(INPUT_ARRAY, comparator);
    assertThat(INPUT_ARRAY).isEqualTo(SORTED);
  }

  @Test
  void whenSortedByComparingInt_thenArraySorted() {
    Arrays.sort(INPUT_ARRAY, Comparator.comparingInt(String::length));
    assertThat(INPUT_ARRAY).isEqualTo(SORTED);
  }

  public static class StringLengthComparator implements Comparator<String> {

    @Override
    public int compare(final String s1, final String s2) {
      return Integer.compare(s1.length(), s2.length());
    }
  }
}
