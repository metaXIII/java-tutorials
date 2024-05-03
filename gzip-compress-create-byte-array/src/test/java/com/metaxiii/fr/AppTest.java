package com.metaxiii.fr;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.GZIPOutputStream;
import org.junit.jupiter.api.Test;

public class AppTest {

  private static final int BUFFER_SIZE = 512;

  @Test
  void whenCompressingUsingGZip_thenGetCompressedByteArray() throws IOException {
    final String payload =
      """
            This is a sample text to test method gzip. The gzip algorithm will compress this string.
            The result will be smaller than this string.
            """;
    final ByteArrayOutputStream os = new ByteArrayOutputStream();
    gzip(new ByteArrayInputStream(payload.getBytes()), os);
    final byte[] compressed = os.toByteArray();
    assertTrue(payload.getBytes().length > compressed.length);
    assertEquals("1f", Integer.toHexString(compressed[0] & 0xFF));
    assertEquals("8b", Integer.toHexString(compressed[1] & 0xFF));
  }

  public static void gzip(final InputStream is, final OutputStream os) throws IOException {
    final GZIPOutputStream gzipOs = new GZIPOutputStream(os);
    final byte[] buffer = new byte[BUFFER_SIZE];
    int bytesRead;
    while ((bytesRead = is.read(buffer)) != -1) {
      gzipOs.write(buffer, 0, bytesRead);
    }
    gzipOs.close();
  }
}
