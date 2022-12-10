package com.metaxiii.fr.errorhandlingforrestwithspring.controller;

import com.metaxiii.fr.errorhandlingforrestwithspring.exceptions.CustomException;
import com.metaxiii.fr.errorhandlingforrestwithspring.model.Foo;
import java.nio.file.AccessDeniedException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class FooController {

  private static final Foo FOO = new Foo(1L, "foo");

  @GetMapping("/")
  ResponseEntity<Foo> findFoo() {
    return new ResponseEntity<>(FOO, HttpStatus.OK);
  }

  @GetMapping("/path")
  ResponseEntity<Foo> findFooThrowsException() {
    throw new CustomException("Foo is not there");
  }

  @GetMapping("/other")
  ResponseEntity<Foo> findFooThrowsAccessException()
    throws AccessDeniedException {
    throw new AccessDeniedException("Not allowed here");
  }

  @ExceptionHandler({ CustomException.class })
  private void handleException() {
    log.error("une erreur est survenue");
  }
}
