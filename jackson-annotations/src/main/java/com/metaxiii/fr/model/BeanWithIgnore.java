package com.metaxiii.fr.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@JsonIgnoreProperties({ "id" })
@Getter
@Setter
@AllArgsConstructor
public class BeanWithIgnore {

  private int id;
  private String name;

  @JsonIgnore
  private int otherId;
}
