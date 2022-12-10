package com.metaxiii.fr.model;

import com.fasterxml.jackson.annotation.JsonFilter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@JsonFilter(value = "myFilter")
public class BeanWithFilter {

  private int id;
  private String name;
}
