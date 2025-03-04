package com.metaxiii.fr.hibernatereactive;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.metaxiii.fr.hibernatereactive")
public class HibernateReactiveApplication {

  public static void main(String[] args) {
    SpringApplication.run(HibernateReactiveApplication.class, args);
  }
}
