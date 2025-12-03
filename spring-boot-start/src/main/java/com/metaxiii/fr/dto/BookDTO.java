package com.metaxiii.fr.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
@AllArgsConstructor
public class BookDTO {

  private Long id;
  private String title;
  private String author;
}
