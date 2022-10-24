package com.metaxiii.fr.model;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class UnwrappedUser {
    private int id;

    @JsonUnwrapped
    private Name name;

    @AllArgsConstructor
    @Getter
    @Setter
    public static class Name {
        private String firstName;
        private String lastName;
    }
}
