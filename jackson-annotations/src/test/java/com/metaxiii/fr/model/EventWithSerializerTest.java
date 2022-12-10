package com.metaxiii.fr.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.junit.jupiter.api.Test;

class EventWithSerializerTest {

  @Test
  void whenSerializingUsingJsonSerialize_thenCorrect()
    throws JsonProcessingException, ParseException {
    SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
    String toParse = "20-12-2014 02:30:00";
    Date date = df.parse(toParse);
    EventWithSerializer event = new EventWithSerializer("party", date);
    String result = new ObjectMapper().writeValueAsString(event);
    System.out.println(result);
    assertTrue(result.contains(toParse));
  }

  @Test
  void whenDeserializingUsingJsonDeserialize_thenCorrect() throws IOException {
    String json = "{\"name\":\"party\",\"eventDate\":\"20-12-2014 02:30:00\"}";
    SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
    EventWithSerializer event = new ObjectMapper()
      .readerFor(EventWithSerializer.class)
      .readValue(json);
    assertEquals("20-12-2014 02:30:00", df.format(event.eventDate));
  }
}
