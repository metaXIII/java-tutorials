package com.metaxiii.fr;

import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class App {

  private static final Logger LOGGER = Logger.getLogger(App.class.getName());

  public static void main(final String[] args) {
    LOGGER.info("Hello world");
  }

  public static int countWithSet(int number) {
    number = Math.abs(number);
    final Set<Character> uniqueDigits = new HashSet<>();
    final String numberStr = String.valueOf(number);
    for (final char digit : numberStr.toCharArray()) {
      uniqueDigits.add(digit);
    }
    return uniqueDigits.size();
  }

  public static long countWithStreamApi(final int number) {
    return String.valueOf(Math.abs(number)).chars().distinct().count();
  }

  public static int countWithBitManipulation(int number) {
    if (number == 0) {
      return 1;
    }
    number = Math.abs(number);
    int mask = 0;
    while (number > 0) {
      final int digit = number % 10;
      mask |= 1 << digit;
      number /= 10;
    }
    return Integer.bitCount(mask);
  }
}
