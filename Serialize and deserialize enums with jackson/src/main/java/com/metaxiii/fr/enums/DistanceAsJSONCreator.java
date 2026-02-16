package com.metaxiii.fr.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum DistanceAsJSONCreator {
  KILOMETER("km", 1000),
  MILE("miles", 1609.34),
  METER("meters", 1),
  INCH("inches", 0.0254),
  CENTIMETER("cm", 0.01),
  MILLIMETER("mm", 0.001);

  private final String unit;
  private final double meters;

  @JsonCreator
  public static DistanceAsJSONCreator forValues(
    @JsonProperty("unit") final String unit,
    @JsonProperty("meters") final double meters
  ) {
    for (final DistanceAsJSONCreator distance : DistanceAsJSONCreator.values()) {
      if (distance.unit.equals(unit) && Double.compare(distance.meters, meters) == 0) {
        return distance;
      }
    }
    return null;
  }
}
