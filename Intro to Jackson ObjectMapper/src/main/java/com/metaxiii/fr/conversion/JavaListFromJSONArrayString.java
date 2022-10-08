package com.metaxiii.fr.conversion;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.metaxiii.fr.model.Car;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class JavaListFromJSONArrayString {

    public static void main(String[] args) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonCarArray = "[{\"color\": \"Black\", \"type\": \"BMW\"}, {\"color\": \"Red\", \"type\": \"Fiat\"}]";
        List<Car> cars = objectMapper.readValue(jsonCarArray, new TypeReference<>() {
        });
        log.info(cars.get(0).getColor());
        log.info(cars.get(1).getColor());

        log.info("in array : ");
        ObjectMapper objectMapperArray = new ObjectMapper();
        objectMapperArray.configure(DeserializationFeature.USE_JAVA_ARRAY_FOR_JSON_ARRAY, true);
        Car[] carsArray = objectMapper.readValue(jsonCarArray, Car[].class);
        log.info(carsArray[0].getColor());

    }
}
