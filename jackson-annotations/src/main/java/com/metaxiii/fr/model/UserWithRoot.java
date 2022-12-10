package com.metaxiii.fr.model;

import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@JsonRootName(value = "user")
@AllArgsConstructor
@NoArgsConstructor
public class UserWithRoot {

  private int id;
  private String name;
}
