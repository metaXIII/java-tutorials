package com.metaxiii.fr;

public class Child extends Parent {

  Child() {
    super();
    System.out.println("Child constructor");
    additionalInitialization();
  }

  private void additionalInitialization() {
    System.out.println("Additional initialization in Child");
  }
}
