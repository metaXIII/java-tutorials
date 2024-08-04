package com.metaxiii.fr;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class StringUtils {

  public static String reverse(final String str) {
    return new StringBuilder(str).reverse().toString();
  }
}
