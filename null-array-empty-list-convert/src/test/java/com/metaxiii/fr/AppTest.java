package com.metaxiii.fr;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.apache.commons.lang3.ArrayUtils;
import org.junit.jupiter.api.Test;

class AppTest {

  @Test
  void whenArrayIsEmpty_thenReturnEmptyList_getAsListApacheCommonsLang() {
    final String[] possiblyNullArray = {};
    assertThat(getAsListApacheCommonsLang(possiblyNullArray)).isNotNull().isEmpty();
  }

  static List<String> getAsListApacheCommonsLang(final String[] args) {
    return Arrays.asList(ArrayUtils.nullToEmpty(args));
  }

  @Test
  void whenArrayIsEmpty_thenReturnEmptyList_getAsListOptional() {
    final String[] possiblyNullArray = {};
    assertThat(getAsListOptional(possiblyNullArray)).isNotNull().isEmpty();
  }

  static List<String> getAsListOptional(final String[] args) {
    return Optional.ofNullable(args).stream().flatMap(Arrays::stream).toList();
  }

  @Test
  void whenArrayIsNotEmpty_thenReturnListWithSameElements_getAsListApacheCommonsLang() {
    final String[] possiblyNullArray = { "a", "b" };
    assertThat(getAsListApacheCommonsLang(possiblyNullArray)).containsExactly(possiblyNullArray);
  }

  @Test
  void whenArrayIsNotEmpty_thenReturnListWithSameElements_getAsListOptional() {
    final String[] possiblyNullArray = { "a", "b" };
    assertThat(getAsListOptional(possiblyNullArray)).containsExactly(possiblyNullArray);
  }

  @Test
  void whenArrayIsNull_thenReturnEmptyList_getAsListApacheCommonsLang() {
    assertThat(getAsListApacheCommonsLang(null)).isNotNull().isEmpty();
  }

  @Test
  void whenArrayIsNull_thenReturnEmptyList_getAsListOptional() {
    assertThat(getAsListOptional(null)).isNotNull().isEmpty();
  }
}
