package com.metaxiii.fr.model;

import com.fasterxml.jackson.annotation.JacksonInject;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class BeanWithInject {
    @JacksonInject
    private int id;
    private String name;
}
