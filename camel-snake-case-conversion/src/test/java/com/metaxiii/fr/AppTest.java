package com.metaxiii.fr;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class AppTest {

  @ParameterizedTest
  @CsvSource(
    value = {
      "'',''",
      "snakecase,snakecase",
      "snake_case,snake_case",
      "convertCamelCase,convert_camel_case",
      "convertCCamelCase,convert_c_camel_case",
      "sn@keCase#,sn@ke_case#",
    }
  )
  void whenConvertAllLowerCaseString_thenGetUnchangedString_Manual(final String input, final String expected) {
    Assertions.assertEquals(expected, App.convertCamelCaseToSnake(input));
    Assertions.assertEquals(expected, App.convertCamelCaseToSnakeRegex(input));
  }
}
