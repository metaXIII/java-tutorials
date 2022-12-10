package com.metaxiii.fr.conversion;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.metaxiii.fr.model.Car;
import java.util.List;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JavaListFromJSONArrayString {

  public List<Car> processAsList(final String json)
    throws JsonProcessingException {
    ObjectMapper objectMapper = new ObjectMapper();
    return objectMapper.readValue(json, new TypeReference<>() {});
  }

  public Car[] processAsArray(final String json)
    throws JsonProcessingException {
    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.configure(
      DeserializationFeature.USE_JAVA_ARRAY_FOR_JSON_ARRAY,
      true
    );
    return objectMapper.readValue(json, Car[].class);
  }
}
