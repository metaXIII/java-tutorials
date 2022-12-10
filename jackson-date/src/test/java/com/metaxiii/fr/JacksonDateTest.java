package com.metaxiii.fr;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.fasterxml.jackson.databind.util.StdDateFormat;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.junit.jupiter.api.Test;

class JacksonDateTest {

  @Test
  void whenSerializingDateWithJackson_thenSerializedToTimestamp()
    throws JsonProcessingException, ParseException {
    SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy hh:mm");
    df.setTimeZone(TimeZone.getTimeZone("UTC"));
    Date date = df.parse("01-01-1970 01:00");
    Event event = new Event("party", date);
    ObjectMapper mapper = new ObjectMapper();
    assertEquals(
      "{\"name\":\"party\",\"eventDate\":3600000}",
      mapper.writeValueAsString(event)
    );
  }

  @Test
  void whenSerializingDateToISO8601_thenSerializedToText()
    throws JsonProcessingException, ParseException {
    SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy hh:mm");
    df.setTimeZone(TimeZone.getTimeZone("UTC"));
    String toParse = "01-01-1970 02:30";
    Date date = df.parse(toParse);
    Event event = new Event("party", date);
    ObjectMapper mapper = new ObjectMapper();
    mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    mapper.setDateFormat(new StdDateFormat().withColonInTimeZone(true));
    String result = mapper.writeValueAsString(event);
    assertEquals(
      "{\"name\":\"party\",\"eventDate\":\"1970-01-01T02:30:00.000+00:00\"}",
      result
    );
  }

  @Test
  void whenSettingObjectMapperDateFormat_thenCorrect()
    throws JsonProcessingException, ParseException {
    SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy hh:mm");
    String toParse = "20-12-2014 02:30";
    Date date = df.parse(toParse);
    Event event = new Event("party", date);
    ObjectMapper mapper = new ObjectMapper();
    mapper.setDateFormat(df);
    String result = mapper.writeValueAsString(event);
    assertEquals(
      "{\"name\":\"party\",\"eventDate\":\"20-12-2014 02:30\"}",
      result
    );
  }

  @Test
  void whenUsingJsonFormatAnnotationToFormatDate_thenCorrect()
    throws JsonProcessingException, ParseException {
    SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
    df.setTimeZone(TimeZone.getTimeZone("UTC"));
    String toParse = "20-12-2014 02:30:00";
    Date date = df.parse(toParse);
    EventWithJSONParse event = new EventWithJSONParse("party", date);
    ObjectMapper mapper = new ObjectMapper();
    String result = mapper.writeValueAsString(event);
    assertEquals(
      "{\"name\":\"party\",\"eventDate\":\"20-12-2014 02:30:00\"}",
      result
    );
  }

  @Test
  void whenUsingCustomDateSerializer_thenCorrect()
    throws JsonProcessingException, ParseException {
    SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
    String toParse = "20-12-2014 02:30:00";
    Date date = df.parse(toParse);
    EventWithCustomSerializer event = new EventWithCustomSerializer(
      "party",
      date
    );
    ObjectMapper mapper = new ObjectMapper();
    String result = mapper.writeValueAsString(event);
    assertEquals(
      "{\"name\":\"party\",\"eventDate\":\"20-12-2014 02:30:00\"}",
      result
    );
  }

  @Getter
  @NoArgsConstructor
  @AllArgsConstructor
  private static class Event {

    private String name;
    private Date eventDate;
  }

  @Getter
  @NoArgsConstructor
  @AllArgsConstructor
  private static class EventWithJSONParse {

    private String name;

    @JsonFormat(
      shape = JsonFormat.Shape.STRING,
      pattern = "dd-MM-yyyy hh:mm:ss"
    )
    private Date eventDate;
  }

  @Getter
  @NoArgsConstructor
  @AllArgsConstructor
  private static class EventWithCustomSerializer {

    private String name;

    @JsonSerialize(using = CustomDateSerializer.class)
    private Date eventDate;
  }

  private static class CustomDateSerializer extends StdSerializer<Date> {

    private static final SimpleDateFormat FORMATTER = new SimpleDateFormat(
      "dd-MM-yyyy hh:mm:ss"
    );

    private CustomDateSerializer() {
      this(null);
    }

    private CustomDateSerializer(Class t) {
      super(t);
    }

    @Override
    public void serialize(
      Date value,
      JsonGenerator gen,
      SerializerProvider arg2
    ) throws IOException {
      gen.writeString(FORMATTER.format(value));
    }
  }
}
