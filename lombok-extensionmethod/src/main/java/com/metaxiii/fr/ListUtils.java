package com.metaxiii.fr;

import java.util.List;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ListUtils {

  public static int sum(final List<? extends Number> list) {
    return list.stream().mapToInt(Number::intValue).sum();
  }
}
