package com.metaxiii.fr.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
@Getter
@Setter
@AllArgsConstructor
public class ItemWithIdentity {
    private int id;
    private String itemName;
    private UserWithIdentity owner;
}
