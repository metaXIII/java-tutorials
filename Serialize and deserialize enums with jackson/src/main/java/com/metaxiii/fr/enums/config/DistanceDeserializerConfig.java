package com.metaxiii.fr.enums.config;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.metaxiii.fr.enums.DistanceDeserializer;
import java.io.IOException;

public class DistanceDeserializerConfig
  extends StdDeserializer<DistanceDeserializer> {

  public DistanceDeserializerConfig() {
    super(DistanceDeserializer.class);
  }

  @Override
  public DistanceDeserializer deserialize(
    final JsonParser jsonParser,
    final DeserializationContext ctxt
  ) throws IOException {
    JsonNode node = jsonParser.getCodec().readTree(jsonParser);
    String unit = node.get("unit").asText();
    double meters = node.get("meters").asDouble();
    for (DistanceDeserializer distance : DistanceDeserializer.values()) {
      if (
        distance.getUnit().equals(unit) &&
        Double.compare(distance.getMeters(), meters) == 0
      ) {
        return distance;
      }
    }
    return null;
  }
}
