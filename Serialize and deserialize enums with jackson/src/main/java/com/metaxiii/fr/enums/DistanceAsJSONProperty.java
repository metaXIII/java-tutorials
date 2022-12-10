package com.metaxiii.fr.enums;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum DistanceAsJSONProperty {
  @JsonProperty("distance-in-km")
  KILOMETER("km", 1000),
  @JsonProperty("distance-in-miles")
  MILE("miles", 1609.34),
  METER("meters", 1),
  INCH("inches", 0.0254),
  CENTIMETER("cm", 0.01),
  MILLIMETER("mm", 0.001);

  private final String unit;
  private final double meters;
}
