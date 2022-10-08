package com.metaxiii.fr.conversion;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.metaxiii.fr.model.Car;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;
import java.net.URL;

@Slf4j
public class JsonToJavaObject {
    public static void main(String[] args) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        String json = "{ \"color\" : \"Black\", \"type\" : \"BMW\" }";
        Car car = objectMapper.readValue(json, Car.class);
        log.info("it should be black: " + car.getColor());
        log.info("it should be BMW: " + car.getType());
        car = objectMapper.readValue(new File("src/test/resources/json_car.json"), Car.class);
        log.info("it should be yellow: " + car.getColor());
        log.info("it should be diesel: " + car.getType());
        car = objectMapper.readValue(new URL("file:src/test/resources/json_car_url.json"), Car.class);
        log.info("it should be pink: " + car.getColor());
        log.info("it should be diesel: " + car.getType());
    }
}
