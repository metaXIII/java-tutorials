package com.metaxiii.fr.config;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.BeanPropertyWriter;
import com.metaxiii.fr.model.User;

public class UpperCasingWriter extends BeanPropertyWriter {
    BeanPropertyWriter writer;

    public UpperCasingWriter(BeanPropertyWriter w) {
        super(w);
        writer = w;
    }

    @Override
    public void serializeAsField(Object bean, JsonGenerator gen,
                                 SerializerProvider prov) throws Exception {
        String value = ((User) bean).name;
        value = (value == null) ? "" : value.toUpperCase();
        gen.writeStringField("name", value);
    }
}
