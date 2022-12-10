package com.metaxiii.fr.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class DuplicateItem {

  private int id;
  private String itemName;
  private User owner;
}
