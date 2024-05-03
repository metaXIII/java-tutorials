package com.metaxiii.fr.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LombokPost {

  private String title;
  private String text;
  private String category;
}
