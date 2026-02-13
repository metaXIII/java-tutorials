package com.metaxiii.fr.conversion;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.metaxiii.fr.model.Car;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JsonToJavaObject {

  public Car processAsObject(String json) throws JsonProcessingException {
    ObjectMapper objectMapper = new ObjectMapper();
    return objectMapper.readValue(json, Car.class);
  }  

  public Car processAsFile(final File file) throws IOException {
    ObjectMapper objectMapper = new ObjectMapper();
    return objectMapper.readValue(file, Car.class);
  }

  public Car processAsUrl(final URL url) throws IOException {
    ObjectMapper objectMapper = new ObjectMapper();
    return objectMapper.readValue(url.openStream(), Car.class);
  }
}
