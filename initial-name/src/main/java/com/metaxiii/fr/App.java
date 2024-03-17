package com.metaxiii.fr;

import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class App {

  String getInitialUsingLoop(final String name) {
    if (name == null || name.isEmpty()) {
      return "";
    }
    final String[] parts = name.split("\\s+");
    final StringBuilder initials = new StringBuilder();
    for (final String part : parts) {
      if (part.matches("[a-zA-Z].*")) {
        initials.append(part.charAt(0));
      }
    }
    return initials.toString().toUpperCase();
  }

  String getInitialUsingRegex(final String name) {
    if (name == null || name.isEmpty()) {
      return "";
    }
    final Pattern pattern = Pattern.compile("\\b[a-zA-Z]");
    final Matcher matcher = pattern.matcher(name);
    final StringBuilder initials = new StringBuilder();
    while (matcher.find()) {
      initials.append(matcher.group());
    }
    return initials.toString().toUpperCase();
  }

  String getInitialUsingStreamsAPI(final String name) {
    if (name == null || name.isEmpty()) {
      return "";
    }
    return Arrays
      .stream(name.split("\\s+"))
      .filter(part -> part.matches("[a-zA-Z].*"))
      .map(part -> part.substring(0, 1))
      .collect(Collectors.joining())
      .toUpperCase();
  }

  String getInitialUsingStringTokenizer(final String name) {
    if (name == null || name.isEmpty()) {
      return "";
    }
    final StringTokenizer tokenizer = new StringTokenizer(name);
    final StringBuilder initials = new StringBuilder();
    while (tokenizer.hasMoreTokens()) {
      final String part = tokenizer.nextToken();
      if (part.matches("[a-zA-Z].*")) {
        initials.append(part.charAt(0));
      }
    }
    return initials.toString().toUpperCase();
  }
}
