package com.metaxiii.fr;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

class AppTest {

  private final LombokMapper lombokMapper = Mappers.getMapper(LombokMapper.class);

  @Test
  void whenDestinationIsMapped_thenIsSuccessful() {
    final SimpleSource simpleSource = new SimpleSource();
    simpleSource.setName("file");
    simpleSource.setDescription("A text file.");
    final SimpleDestination simpleDestination = lombokMapper.sourceToDestination(simpleSource);
    Assertions.assertNotNull(simpleDestination);
    Assertions.assertEquals(simpleSource.getName(), simpleDestination.getName());
    Assertions.assertEquals(simpleSource.getDescription(), simpleDestination.getDescription());
    final LombokDestination lombokDestination = lombokMapper.sourceToLombokDestination(simpleSource);
    Assertions.assertNotNull(lombokDestination);
    Assertions.assertEquals(simpleSource.getName(), lombokDestination.getName());
    Assertions.assertEquals(simpleSource.getDescription(), lombokDestination.getDescription());
  }
}
