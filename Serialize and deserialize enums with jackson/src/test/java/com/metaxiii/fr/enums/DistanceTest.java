package com.metaxiii.fr.enums;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.metaxiii.fr.model.City;
import com.metaxiii.fr.model.CityWithDistanceAsJsonCreator;
import com.metaxiii.fr.model.CityWithDistanceAsJsonCustomDeserializor;
import com.metaxiii.fr.model.CityWithDistanceAsJsonProperty;
import com.metaxiii.fr.model.CityWithDistanceAsJsonValue;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DistanceTest {

    @Test
    void itShouldGiveDefaultEnumRepresentation() throws JsonProcessingException {
        final ObjectMapper objectMapper = new ObjectMapper();
        final String string = objectMapper.writeValueAsString(Distance.MILE);
        assertEquals("\"MILE\"", string);
    }

    @Test
    void itShouldGiveJSONEnumRepresentationWhenDistanceAsJSONObject() throws JsonProcessingException {
        final ObjectMapper objectMapper = new ObjectMapper();
        final String string = objectMapper.writeValueAsString(DistanceAsJSONObject.MILE);
        assertEquals("{\"unit\":\"miles\",\"meters\":1609.34}", string);
    }

    @Test
    void itShouldGiveJSONEnumRepresentationWhenDistanceAsJSONValue() throws JsonProcessingException {
        final ObjectMapper objectMapper = new ObjectMapper();
        final String string = objectMapper.writeValueAsString(DistanceAsJsonValue.MILE);
        assertEquals("1609.34", string);
    }

    @Test
    void itShouldGiveJSONEnumRepresentationWhenDistanceAsDistanceSerializer() throws JsonProcessingException {
        final ObjectMapper objectMapper = new ObjectMapper();
        final String string = objectMapper.writeValueAsString(DistanceSerializer.MILE);
        assertEquals("{\"name\":\"MILE\",\"unit\":\"miles\",\"meters\":1609.34}", string);
    }

    @Test
    void itShouldDeserializeJsonToEnumWithDefaultBehavior() throws JsonProcessingException {
        City city = new ObjectMapper().readValue("{\"distance\":\"KILOMETER\"}", City.class);
        assertEquals(Distance.KILOMETER, city.getDistance());
    }

    @Test
    void itShouldDeserializeJsonToEnumUsingJsonValue() throws JsonProcessingException {
        CityWithDistanceAsJsonValue city = new ObjectMapper().readValue("{\"distance\":\"0.0254\"}", CityWithDistanceAsJsonValue.class);
        assertEquals(DistanceAsJsonValue.INCH, city.getDistance());
    }

    @Test
    void itShouldDeserializeJsonToEnumUsingJsonproperties() throws JsonProcessingException {
        CityWithDistanceAsJsonProperty city = new ObjectMapper().readValue("{\"distance\": \"distance-in-km\"}", CityWithDistanceAsJsonProperty.class);
        assertEquals(DistanceAsJSONProperty.KILOMETER, city.getDistance());
    }

    @Test
    void itShouldDeserializeJsonToEnumUsingJsonCreator() throws JsonProcessingException {
        CityWithDistanceAsJsonCreator city = new ObjectMapper().readValue("""
                {
                    "distance": {
                        "unit":"miles",
                        "meters":1609.34
                    }
                }""", CityWithDistanceAsJsonCreator.class);
        assertEquals(DistanceAsJSONCreator.MILE, city.getDistance());
    }

    @Test
    void itShouldDeserializeJsonToEnumUsingCustomDeserializor() throws JsonProcessingException {
        CityWithDistanceAsJsonCustomDeserializor city = new ObjectMapper().readValue("""
                {
                    "distance": {
                        "unit":"miles",
                        "meters":1609.34
                    }
                }""", CityWithDistanceAsJsonCustomDeserializor.class);
        assertEquals(DistanceDeserializer.MILE, city.getDistance());
    }
}
