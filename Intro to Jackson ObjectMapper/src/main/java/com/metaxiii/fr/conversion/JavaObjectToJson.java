package com.metaxiii.fr.conversion;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.metaxiii.fr.model.Car;
import java.io.File;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JavaObjectToJson {

  public String process(final Car car) throws IOException {
    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.writeValue(new File("target/car.json"), car);
    return objectMapper.writeValueAsString(car);
  }
}
