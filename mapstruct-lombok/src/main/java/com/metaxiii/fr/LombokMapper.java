package com.metaxiii.fr;

import org.mapstruct.Mapper;

@Mapper
public interface LombokMapper {
  SimpleDestination sourceToDestination(SimpleSource source);
  LombokDestination sourceToLombokDestination(SimpleSource source);
}
