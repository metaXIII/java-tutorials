package com.metaxiii.fr.conversion;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.metaxiii.fr.conversion.custom.CustomCarDeserializer;
import com.metaxiii.fr.conversion.custom.CustomCarSerializer;
import com.metaxiii.fr.model.Car;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CustomSerializerDeserializer {
    public static void main(String[] args) throws JsonProcessingException {
        log.info("a custom serializer can be invoke like this : ");
        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module =
                new SimpleModule("CustomCarSerializer", new Version(1, 0, 0, null, null, null));
        module.addSerializer(Car.class, new CustomCarSerializer());
        mapper.registerModule(module);
        Car car = new Car("yellow", "renault");
        String carJson = mapper.writeValueAsString(car);
        log.info(carJson);


        log.info("a custom deserializer can be invoke like this : ");
        String json = "{ \"color\" : \"Black\", \"type\" : \"BMW\" }";
        ObjectMapper deserializeMapper = new ObjectMapper();
        SimpleModule deserializeModule =
                new SimpleModule("CustomCarDeserializer", new Version(1, 0, 0, null, null, null));
        deserializeModule.addDeserializer(Car.class, new CustomCarDeserializer());
        deserializeMapper.registerModule(module);
        Car deserializeCar = mapper.readValue(json, Car.class);
        log.info(deserializeCar.getColor());

    }
}
