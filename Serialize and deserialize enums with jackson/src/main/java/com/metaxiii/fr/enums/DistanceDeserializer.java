package com.metaxiii.fr.enums;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.metaxiii.fr.enums.config.DistanceDeserializerConfig;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
@JsonDeserialize(using = DistanceDeserializerConfig.class)
public enum DistanceDeserializer {
    KILOMETER("km", 1000),
    MILE("miles", 1609.34),
    METER("meters", 1),
    INCH("inches", 0.0254),
    CENTIMETER("cm", 0.01),
    MILLIMETER("mm", 0.001);

    private final String unit;
    private final double meters;
}
