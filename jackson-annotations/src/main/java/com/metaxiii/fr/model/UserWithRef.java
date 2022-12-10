package com.metaxiii.fr.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserWithRef {

  private int id;
  private String name;

  @JsonBackReference
  public List<ItemWithRef> userItems;

  public UserWithRef(final int id, final String name) {
    this.id = id;
    this.name = name;
    this.userItems = new ArrayList<>();
  }

  public void addItem(final ItemWithRef item) {
    this.userItems.add(item);
  }
}
