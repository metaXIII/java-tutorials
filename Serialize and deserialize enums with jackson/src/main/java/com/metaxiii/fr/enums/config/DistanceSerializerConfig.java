package com.metaxiii.fr.enums.config;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.metaxiii.fr.enums.DistanceSerializer;

import java.io.IOException;

public class DistanceSerializerConfig extends StdSerializer<DistanceSerializer> {

    public DistanceSerializerConfig() {
        super(DistanceSerializer.class);
    }

    @Override
    public void serialize(final DistanceSerializer distance, final JsonGenerator gen,
                          final SerializerProvider provider) throws IOException {
        gen.writeStartObject();
        gen.writeFieldName("name");
        gen.writeString((distance.name()));
        gen.writeFieldName("unit");
        gen.writeString(distance.getUnit());
        gen.writeFieldName("meters");
        gen.writeNumber(distance.getMeters());
        gen.writeEndObject();
    }
}
