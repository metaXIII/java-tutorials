package com.metaxiii.fr;

import java.security.SecureRandom;
import java.util.Random;
import org.apache.commons.math3.random.RandomDataGenerator;

public class App {

  String generateRandomHexUsingRandomNextIntWithInRange(final int lower, final int upper) {
    final var random = getRandom();
    final var randomInt = random.nextInt(upper - lower) + lower;
    return Integer.toHexString(randomInt);
  }

  @SuppressWarnings({ "java:S2245" })
  private static Random getRandom() {
    return new Random();
  }

  String generateRandomHexUsingSecureRandomNextInt() {
    final var secureRandom = new SecureRandom();
    final var randomInt = secureRandom.nextInt();
    return Integer.toHexString(randomInt);
  }

  String generateRandomHexUsingSecureRandomNextIntWithInRange(final int lower, final int upper) {
    final var secureRandom = new SecureRandom();
    final var randomInt = secureRandom.nextInt(upper - lower) + lower;
    return Integer.toHexString(randomInt);
  }

  String generateRandomHexUsingSecureRandomNextLong() {
    final var secureRandom = new SecureRandom();
    final var randomLong = secureRandom.nextLong();
    return Long.toHexString(randomLong);
  }

  String generateRandomHexWithCommonsMathRandomDataGenerator(final int len) {
    final var randomDataGenerator = new RandomDataGenerator();
    return randomDataGenerator.nextHexString(len);
  }

  String generateRandomHexWithCommonsMathRandomDataGeneratorNextIntWithRange(final int lower, final int upper) {
    final var randomDataGenerator = new RandomDataGenerator();
    final var randomInt = randomDataGenerator.nextInt(lower, upper);
    return Integer.toHexString(randomInt);
  }

  String generateRandomHexWithCommonsMathRandomDataGeneratorSecureNextIntWithRange(final int lower, final int upper) {
    final var randomDataGenerator = new RandomDataGenerator();
    final var randomInt = randomDataGenerator.nextSecureInt(lower, upper);
    return Integer.toHexString(randomInt);
  }

  String generateRandomHexWithStringFormatter() {
    final var random = getRandom();
    final var randomInt = random.nextInt();
    return String.format("%02x", randomInt);
  }

  String generateSecureRandomHexWithCommonsMathRandomDataGenerator(final int len) {
    final var randomDataGenerator = new RandomDataGenerator();
    return randomDataGenerator.nextSecureHexString(len);
  }

  String generateUnboundedRandomHexUsingRandomNextInt() {
    final var random = getRandom();
    final var randomInt = random.nextInt();
    return Integer.toHexString(randomInt);
  }

  String generateUnboundedRandomHexUsingRandomNextLong() {
    final var random = getRandom();
    final var randomLong = random.nextLong();
    return Long.toHexString(randomLong);
  }
}
