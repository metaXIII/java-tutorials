package com.metaxiii.fr.model;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class Zoo {

  public Animal animal;

  @JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
  @JsonSubTypes(
    { @JsonSubTypes.Type(value = Dog.class, name = "dog"), @JsonSubTypes.Type(value = Cat.class, name = "cat") }
  )
  @AllArgsConstructor
  @NoArgsConstructor
  public static class Animal {

    public String name;
  }

  @JsonTypeName("dog")
  @NoArgsConstructor
  public static class Dog extends Animal {

    public double barkVolume;

    public Dog(final String name) {
      this.name = name;
    }
  }

  @JsonTypeName("cat")
  @NoArgsConstructor
  public static class Cat extends Animal {

    boolean likesCream;
    public int lives;

    public Cat(final String name) {
      this.name = name;
    }
  }
}
