package com.metaxiii.fr.model;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class User {

  private int id;

  @JsonView(value = Views.Public.class)
  public String name;
}
