package com.metaxiii.fr.conversion;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.metaxiii.fr.model.Car;
import com.metaxiii.fr.model.Request;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HandlingDateFormat {

  public String process(final Car car) throws JsonProcessingException {
    ObjectMapper objectMapper = new ObjectMapper();
    DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm a z");
    objectMapper.setDateFormat(df);
    Request request = new Request();
    request.setCar(car);
    request.setDatePurcharsed(new Date());
    return objectMapper.writeValueAsString(request);
  }
}
