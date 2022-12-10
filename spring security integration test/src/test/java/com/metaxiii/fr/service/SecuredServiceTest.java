package com.metaxiii.fr.service;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.test.context.support.WithMockUser;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SecuredServiceTest {

  @Autowired
  private SecuredService service;

  @Test
  void givenUnauthenticated_whenCallService_thenThrowsException() {
    assertThrows(
      AuthenticationCredentialsNotFoundException.class,
      () -> service.sayHelloSecured()
    );
  }

  @WithMockUser(username = "spring")
  @Test
  void givenAuthenticated_whenCallServiceWithSecured_thenOk() {
    assertFalse(service.sayHelloSecured().isEmpty());
  }
}
