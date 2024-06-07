package com.metaxiii.fr;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.w3c.dom.Document;

class AppTest {

  private static final String startXml = """
      <?xml version="1.0" encoding="UTF-8" standalone="no"?>""";

  @Test
  void givenXMLDocument_thenConvertToStringSuccessfully() throws Exception {
    final Document document = App.getDocument();
    assertEquals(startXml + App.FRUIT_XML, App.toString(document));
  }

  @Test
  void givenXMLDocument_thenConvertToStringWithOptionSuccessfully() throws Exception {
    final Document document = App.getDocument();
    assertEquals(App.FRUIT_XML, App.toStringWithOptions(document));
  }
}
