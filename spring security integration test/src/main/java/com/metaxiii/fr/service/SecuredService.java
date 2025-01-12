package com.metaxiii.fr.service;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@Service
public class SecuredService {

  @PreAuthorize(value = "hasRole('ROLE_USER')")
  public String sayHelloSecured() {
    return "Hello user.";
  }
}
