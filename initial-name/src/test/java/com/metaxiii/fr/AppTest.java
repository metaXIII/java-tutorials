package com.metaxiii.fr;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class AppTest {

  private App app;

  @BeforeEach
  public void beforeEach() {
    app = new App();
  }

  @ParameterizedTest
  @CsvSource(
    {
      "John F Kennedy,JFK",
      ",''",
      "'',''",
      "Not Correct   88text,NC",
      "michael jackson,MJ",
      "123,''",
      "123 234A,''",
      "1test 2test, ''",
    }
  )
  void getInitialFromName_usingLoop(final String input, final String expected) {
    final String initial = app.getInitialUsingLoop(input);
    assertEquals(expected, initial);
  }

  @ParameterizedTest
  @CsvSource(
    {
      "John F Kennedy,JFK",
      ",''",
      "'',''",
      "Not Correct   88text,NC",
      "michael jackson,MJ",
      "123,''",
      "123 234A,''",
      "1test 2test, ''",
    }
  )
  void getInitialUsingRegex(final String input, final String expected) {
    final String initial = app.getInitialUsingRegex(input);
    assertEquals(expected, initial);
  }

  @ParameterizedTest
  @CsvSource(
    {
      "John F Kennedy,JFK",
      ",''",
      "'',''",
      "Not Correct   88text,NC",
      "michael jackson,MJ",
      "123,''",
      "123 234A,''",
      "1test 2test, ''",
    }
  )
  void getInitialUsingStreamsAPI(final String input, final String expected) {
    final String initial = app.getInitialUsingStreamsAPI(input);
    assertEquals(expected, initial);
  }

  @ParameterizedTest
  @CsvSource(
    {
      "John F Kennedy,JFK",
      ",''",
      "'',''",
      "Not Correct   88text,NC",
      "michael jackson,MJ",
      "123,''",
      "123 234A,''",
      "1test 2test, ''",
    }
  )
  void getInitialUsingStringTokenizer(final String input, final String expected) {
    final String initial = app.getInitialUsingStringTokenizer(input);
    assertEquals(expected, initial);
  }
}
