package com.metaxiii.fr.errorhandlingforrestwithspring.controller;

import static org.junit.jupiter.api.Assertions.*;

import org.apache.catalina.connector.Connector;
import org.apache.catalina.connector.Request;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

class ApiExceptionHandlerTest {

  private ApiExceptionHandler apiExceptionHandler;

  @BeforeEach
  public void init() {
    this.apiExceptionHandler = new ApiExceptionHandler();
  }

  @AfterEach
  public void endEach() {
    this.apiExceptionHandler = null;
  }

  @Test
  void handleAccessDeniedException() {
    assertDoesNotThrow(() -> {
      final ResponseEntity<Object> custom_exception = apiExceptionHandler.handleAccessDeniedException(
        new Exception("custom exception"),
        getWebRequest()
      );
      assertEquals(403, custom_exception.getStatusCodeValue());
      assertEquals("Access denied message here", custom_exception.getBody());
    });
  }

  private WebRequest getWebRequest() {
    final Connector connector = new Connector();
    final Request request = new Request(connector);
    request.setCoyoteRequest(new org.apache.coyote.Request());
    return new ServletWebRequest(request);
  }
}
