package com.metaxiii.fr.conversion;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

@Slf4j
public class JavaMapFromJSONString {
    public static void main(String[] args) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonCarArray = "{\"color\": \"Black\", \"type\": \"BMW\"}";
        Map<String, Object> cars = objectMapper.readValue(jsonCarArray, new TypeReference<>() {
        });
        log.info(cars.keySet().toString());
        log.info(cars.values().toString());
    }
}
