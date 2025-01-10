package com.metaxiii.fr.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BeanWithCreator {

  private int id;
  private String name;

  @JsonCreator
  public BeanWithCreator(@JsonProperty("custom_id") int id, @JsonProperty("custom_name") String name) {
    this.id = id;
    this.name = name;
  }
}
