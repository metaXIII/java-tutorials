package com.metaxiii.fr.conversion;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import com.metaxiii.fr.model.Car;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SerializationDeserialization {
    public static void main(String[] args) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        String json = "{ \"color\" : \"Black\", \"type\" : \"BMW\", \"year\" : \"2022\" }";
        try {
            objectMapper.readValue(json, Car.class);
        } catch (UnrecognizedPropertyException exception) {
            log.info("\"objectMapper.readValue(json, Car.class)\" does not work because new property is here");
        }
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        final Car car = objectMapper.readValue(json, Car.class);
        log.info(car.getColor());

        JsonNode jsonNodeRoot = objectMapper.readTree(json);
        JsonNode jsonNodeYear = jsonNodeRoot.get("year");
        String year = jsonNodeYear.asText();

        log.info(year);
    }
}
