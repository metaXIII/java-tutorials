package com.metaxiii.fr;

public class App {

  private App() {
    throw new IllegalStateException("Utility class");
  }

  public static String convertCamelCaseToSnake(final String input) {
    final var result = new StringBuilder();
    for (final var c : input.toCharArray()) {
      if (Character.isUpperCase(c)) {
        result.append("_").append(Character.toLowerCase(c));
      } else {
        result.append(c);
      }
    }
    return result.toString();
  }

  public static String convertCamelCaseToSnakeRegex(final String input) {
    return input.replaceAll("([A-Z])(?=[A-Z])", "$1_").replaceAll("([a-z])([A-Z])", "$1_$2").toLowerCase();
  }
}
