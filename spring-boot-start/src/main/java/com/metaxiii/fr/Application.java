package com.metaxiii.fr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.persistence.autoconfigure.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan("com.metaxiii.fr.entity")
@EnableJpaRepositories("com.metaxiii.fr.repository")
public class Application {

  public static void main(final String[] args) {
    SpringApplication.run(Application.class, args);
  }
}
