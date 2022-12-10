package com.metaxiii.fr.conversion;

import static org.junit.jupiter.api.Assertions.*;

import com.metaxiii.fr.model.Car;
import java.io.File;
import java.net.URL;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class JsonToJavaObjectTest {

  private JsonToJavaObject jsonToJavaObject;
  private static final String JSON = "{\"color\":\"Black\",\"type\":\"BMW\"}";
  private static final File FILE = new File("src/test/resources/json_car.json");

  @BeforeEach
  public void init() {
    this.jsonToJavaObject = new JsonToJavaObject();
  }

  @AfterEach
  public void endEach() {
    this.jsonToJavaObject = null;
  }

  @Test
  void processAsObject() {
    assertDoesNotThrow(() -> {
      final Car car = jsonToJavaObject.processAsObject(JSON);
      assertEquals("Black", car.getColor());
      assertEquals("BMW", car.getType());
    });
  }

  @Test
  void processAsFile() {
    assertDoesNotThrow(() -> {
      final Car car = jsonToJavaObject.processAsFile(FILE);
      assertEquals("yellow", car.getColor());
      assertEquals("diesel", car.getType());
    });
  }

  @Test
  void processAsUrl() {
    assertDoesNotThrow(() -> {
      final URL url = new URL("file:src/test/resources/json_car_url.json");
      final Car car = jsonToJavaObject.processAsUrl(url);
      assertEquals("pink", car.getColor());
      assertEquals("diesel", car.getType());
    });
  }
}
