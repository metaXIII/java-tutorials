package com.metaxiii.fr.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BeanProperty {
    private int id;
    @JsonProperty(value = "my_name")
    private String name;
}
