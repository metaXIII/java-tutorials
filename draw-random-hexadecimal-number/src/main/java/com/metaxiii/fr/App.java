package com.metaxiii.fr;

import java.security.SecureRandom;
import org.apache.commons.math3.random.RandomDataGenerator;

public final class App {

  private static final SecureRandom SECURE_RANDOM = new SecureRandom();

  private App() {
  }

  public static String generateRandomHexUsingRandomNextIntWithInRange(final int lower, final int upper) {
    final var randomInt = SECURE_RANDOM.nextInt(upper - lower) + lower;
    return Integer.toHexString(randomInt);
  }

  public static String generateRandomHexUsingSecureRandomNextInt() {
    final var randomInt = SECURE_RANDOM.nextInt();
    return Integer.toHexString(randomInt);
  }

  public static String generateRandomHexUsingSecureRandomNextLong() {
    final var randomLong = SECURE_RANDOM.nextLong();
    return Long.toHexString(randomLong);
  }

  public static String generateRandomHexWithCommonsMathRandomDataGenerator(final int len) {
    final var randomDataGenerator = new RandomDataGenerator();
    return randomDataGenerator.nextHexString(len);
  }

  public static String generateRandomHexWithCommonsMathRandomDataGeneratorNextIntWithRange(
      final int lower, final int upper) {
    final var randomDataGenerator = new RandomDataGenerator();
    final var randomInt = randomDataGenerator.nextInt(lower, upper);
    return Integer.toHexString(randomInt);
  }

  public static String generateRandomHexWithCommonsMathRandomDataGeneratorSecureNextIntWithRange(
      final int lower, final int upper) {
    final var randomDataGenerator = new RandomDataGenerator();
    final var randomInt = randomDataGenerator.nextSecureInt(lower, upper);
    return Integer.toHexString(randomInt);
  }

  public static String generateRandomHexWithStringFormatter() {
    final var randomInt = SECURE_RANDOM.nextInt();
    return String.format("%02x", randomInt);
  }

  public static String generateSecureRandomHexWithCommonsMathRandomDataGenerator(final int len) {
    final var randomDataGenerator = new RandomDataGenerator();
    return randomDataGenerator.nextSecureHexString(len);
  }
}
