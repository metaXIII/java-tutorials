package com.metaxiii.fr.model;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Views {

  public interface Public {}

  public interface Internal extends Public {}
}
