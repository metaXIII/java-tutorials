package com.metaxiii.fr.model;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Item {

  @JsonView(Views.Public.class)
  public int id;

  @JsonView(Views.Public.class)
  public String itemName;

  @JsonView(Views.Internal.class)
  public String ownerName;
}
