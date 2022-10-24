package com.metaxiii.fr.model;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@JsonPropertyOrder({"custom_name", "id"})
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MyBean {
    @Getter
    private int id;
    private String name;

    @JsonGetter(value = "custom_name")
    public String getName() {
        return name;
    }

    @JsonSetter("other")
    public void setTheName(String name) {
        this.name = name;
    }
}
