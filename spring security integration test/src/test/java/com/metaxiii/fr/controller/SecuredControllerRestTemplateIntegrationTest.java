package com.metaxiii.fr.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.resttestclient.TestRestTemplate;
import org.springframework.boot.resttestclient.autoconfigure.AutoConfigureTestRestTemplate;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@AutoConfigureTestRestTemplate
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SecuredControllerRestTemplateIntegrationTest {

  @Autowired
  private TestRestTemplate testRestTemplate;

  @Test
  void givenAuthRequestOnPrivateService_shouldSucceedWith200() {
    final ResponseEntity<String> result = testRestTemplate
      .withBasicAuth("spring", "secret")
      .getForEntity("/private/hello", String.class);
    assertEquals(HttpStatus.OK, result.getStatusCode());
  }

  @Test
  void givenRequestOnPrivateService_shouldFailWith401() {
    final ResponseEntity<String> result = testRestTemplate.getForEntity("/private/hello", String.class);
    assertEquals(HttpStatus.UNAUTHORIZED, result.getStatusCode());
  }
}
