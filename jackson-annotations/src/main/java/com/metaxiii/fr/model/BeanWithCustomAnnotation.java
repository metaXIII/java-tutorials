package com.metaxiii.fr.model;

import com.metaxiii.fr.config.CustomAnnotation;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@AllArgsConstructor
@Getter
@Setter
@CustomAnnotation
public class BeanWithCustomAnnotation {
    private int id;
    private String name;
    private Date dateCreated;
}
