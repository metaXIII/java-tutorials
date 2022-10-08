package com.metaxiii.fr.conversion;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.metaxiii.fr.model.Car;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;

@Slf4j
public class JavaObjectToJson {
    public static void main(String[] args) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        Car car = new Car("black", "electric");
        objectMapper.writeValue(new File("target/car.json"), car);
        String carAsString = objectMapper.writeValueAsString(car);
        log.info(carAsString);
    }
}
