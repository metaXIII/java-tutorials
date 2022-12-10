package com.metaxiii.fr.model;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Item {

  @JsonView(Views.Public.class)
  private int id;

  @JsonView(Views.Public.class)
  private String itemName;

  @JsonView(Views.Internal.class)
  private String ownerName;
}
