package com.metaxiii.fr;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

class AppTest {

  public static Stream<Arguments> argumentsProvider() {
    return Stream.of(Arguments.of(1, 256), Arguments.of(0, 1024));
  }

  @ParameterizedTest
  @MethodSource(value = "argumentsProvider")
  void itShouldGenerateRandomHexUsingRandomNextIntWithInRange(final int lower, final int upper) {
    assertThat(App.generateRandomHexUsingRandomNextIntWithInRange(lower, upper)).isNotNull();
  }

  @Test
  void itShouldGenerateRandomHexUsingSecureRandomNextInt() {
    assertThat(App.generateRandomHexUsingSecureRandomNextInt()).isNotNull();
  }

  @Test
  void itShouldGenerateRandomHexUsingSecureRandomNextLong() {
    assertThat(App.generateRandomHexUsingSecureRandomNextLong()).isNotNull();
  }

  @ParameterizedTest
  @ValueSource(ints = { 1, 2, 3, 4, 5, 6, 7, 8, 9 })
  void itShouldGenerateRandomHexWithCommonsMathRandomDataGenerator(final int len) {
    assertThat(App.generateRandomHexWithCommonsMathRandomDataGenerator(len)).isNotNull();
  }

  @ParameterizedTest
  @MethodSource(value = "argumentsProvider")
  void itShouldGenerateRandomHexWithCommonsMathRandomDataGeneratorNextIntWithRange(
      final int lower, final int upper) {
    assertThat(
        App.generateRandomHexWithCommonsMathRandomDataGeneratorNextIntWithRange(lower, upper))
        .isNotNull();
  }

  @ParameterizedTest
  @MethodSource(value = "argumentsProvider")
  void itShouldGenerateRandomHexWithCommonsMathRandomDataGeneratorSecureNextIntWithRange(
      final int lower, final int upper) {
    assertThat(
        App.generateRandomHexWithCommonsMathRandomDataGeneratorSecureNextIntWithRange(
            lower, upper))
        .isNotNull();
  }

  @Test
  void itShouldGenerateRandomHexWithStringFormatter() {
    assertThat(App.generateRandomHexWithStringFormatter()).isNotNull();
  }

  @ParameterizedTest
  @ValueSource(ints = { 1, 2, 3, 4, 5, 6, 7, 8, 9 })
  void itShouldGenerateSecureRandomHexWithCommonsMathRandomDataGenerator(final int len) {
    assertThat(App.generateSecureRandomHexWithCommonsMathRandomDataGenerator(len)).isNotNull();
  }
}
