package com.metaxiii.fr;

import java.util.logging.Logger;

public class Parent {

  private static final Logger LOGGER = Logger.getLogger(Parent.class.getName());

  Parent() {
    LOGGER.info("Parent constructor");
  }
}
