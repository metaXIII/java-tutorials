package com.metaxiii.fr;

import java.util.logging.Logger;

public class Child extends Parent {

  private static final Logger LOGGER = Logger.getLogger(Child.class.getName());

  Child() {
    super();
    LOGGER.info("Child constructor");
    additionalInitialization();
  }

  private void additionalInitialization() {
    LOGGER.info("Additional initialization in Child");
  }
}
