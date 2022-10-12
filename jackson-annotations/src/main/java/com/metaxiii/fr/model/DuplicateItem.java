package com.metaxiii.fr.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class DuplicateItem {
    public int id;
    public String itemName;
    public User owner;
}
