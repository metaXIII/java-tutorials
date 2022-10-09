package com.metaxiii.fr.model;

import com.metaxiii.fr.enums.DistanceDeserializer;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CityWithDistanceAsJsonCustomDeserializor {
    private DistanceDeserializer distance;
}
