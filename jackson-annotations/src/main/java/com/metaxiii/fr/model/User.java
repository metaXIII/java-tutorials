package com.metaxiii.fr.model;

import com.fasterxml.jackson.annotation.JsonIgnoreType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class User {
    private int id;
    private Name name;

    @Getter
    @Setter
    @AllArgsConstructor
    @JsonIgnoreType
    public static class Name {
        private String firstName;
        private String lastName;
    }
}
