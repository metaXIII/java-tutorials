package com.metaxiii.fr;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.ByteBuffer;
import org.junit.jupiter.api.Test;

class AppTest {

  private static final int VALUE = 200;

  @Test
  void givenIntInRange_whenUsingByteBuffer_thenConvertToUnsignedByte() {
    final var buffer = ByteBuffer.allocate(4).putInt(VALUE);
    final var unsignedByte = buffer.array()[3];
    assertEquals(VALUE, Byte.toUnsignedInt(unsignedByte));
  }

  @Test
  void givenInt_whenUsingTypeCastingAndBitMasking_thenConvertToUnsignedByte() {
    final var unsignedByte = (byte) (VALUE & 0xFF);
    assertEquals(VALUE, Byte.toUnsignedInt(unsignedByte));
  }
}
