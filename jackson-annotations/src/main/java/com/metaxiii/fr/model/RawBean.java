package com.metaxiii.fr.model;

import com.fasterxml.jackson.annotation.JsonRawValue;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class RawBean {
    private String name;
    @JsonRawValue
    private String json;
}
