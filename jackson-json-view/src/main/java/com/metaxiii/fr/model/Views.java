package com.metaxiii.fr.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Views {

  @Getter
  @NoArgsConstructor(access = AccessLevel.PRIVATE)
  public static class Public {

    private static final String CLASS_NAME = "Public";
  }

  @Getter
  public static class Internal extends Public {

    private static final String CLASS_NAME = "Internal";
  }
}
