package com.metaxiii.fr.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum TypeEnumWithValue {
    TYPE1(1, "Type A"),
    TYPE2(2, "Type B");

    @Getter
    private final Integer id;
    private final String name;

    @JsonValue
    public String getName() {
        return name;
    }
}
