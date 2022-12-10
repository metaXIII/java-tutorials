package com.metaxiii.fr.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AliasBean {

  @JsonAlias({ "fName", "f_name" })
  private String firstName;

  private String lastName;
}
