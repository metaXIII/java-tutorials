package com.metaxiii.fr.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SecuredControllerRestTemplateIntegrationTest {

  @Autowired
  private TestRestTemplate template;

  @Test
  void givenAuthRequestOnPrivateService_shouldSucceedWith200() {
    ResponseEntity<String> result = template
      .withBasicAuth("spring", "secret")
      .getForEntity("/private/hello", String.class);
    assertEquals(HttpStatus.OK, result.getStatusCode());
  }

  @Test
  void givenRequestOnPrivateService_shouldFailWith401() {
    ResponseEntity<String> result = template.getForEntity("/private/hello", String.class);
    assertEquals(HttpStatus.UNAUTHORIZED, result.getStatusCode());
  }
}
