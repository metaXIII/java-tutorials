package com.metaxiii.fr.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Getter
@Setter
public class UserWithIdentity {

  private int id;
  private String name;
  private List<ItemWithIdentity> userItems;

  public UserWithIdentity(final int id, final String name) {
    this.id = id;
    this.name = name;
    this.userItems = new ArrayList<>();
  }

  public void addItem(final ItemWithIdentity item) {
    this.userItems.add(item);
  }
}
