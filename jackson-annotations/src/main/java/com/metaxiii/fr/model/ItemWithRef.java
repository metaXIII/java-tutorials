package com.metaxiii.fr.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class ItemWithRef {

  private int id;
  private String name;

  @JsonManagedReference
  public UserWithRef owner;
}
