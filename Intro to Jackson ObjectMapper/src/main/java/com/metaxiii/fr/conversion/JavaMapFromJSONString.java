package com.metaxiii.fr.conversion;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JavaMapFromJSONString {

  public Map<String, Object> process(final String json) throws JsonProcessingException {
    ObjectMapper objectMapper = new ObjectMapper();
    return objectMapper.readValue(json, new TypeReference<>() {});
  }
}
