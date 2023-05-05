package com.metaxiii.fr;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.google.common.base.Charsets;
import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import com.google.common.io.ByteSink;
import com.google.common.io.CharSink;
import com.google.common.io.Files;
import java.io.File;
import java.io.IOException;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

@SuppressWarnings("deprecation")
class GuavaFileTest {
  private File file;

  @BeforeEach
  public void init() {
    file = new File("test.txt");
  }

  @AfterEach
  public void endEach() {
    if (file.delete()) {
      System.out.println("file deleted");
    }
  }

  @Test
  void whenWriteUsingFiles_thenWritten() throws IOException {
    final var expectedValue = "Hello world";
    final var file = new File("test.txt");
    Files.write(expectedValue, file, Charsets.UTF_8);
    final var result = Files.toString(file, Charsets.UTF_8);
    assertEquals(expectedValue, result);
  }

  @Test
  void whenWriteUsingCharSink_thenWritten() throws IOException {
    final var expectedValue = "Hello world";
    CharSink sink = Files.asCharSink(file, Charsets.UTF_8);
    sink.write(expectedValue);
    final var result = Files.toString(file, Charsets.UTF_8);
    assertEquals(expectedValue, result);
  }

  @Test
  void whenWriteMultipleLinesUsingCharSink_thenWritten() throws IOException {
    List<String> names = Lists.newArrayList("John", "Jane", "Adam", "Tom");
    CharSink sink = Files.asCharSink(file, Charsets.UTF_8);
    sink.writeLines(names, " ");

    final var result = Files.toString(file, Charsets.UTF_8);
    final var expectedValue = Joiner.on(" ").join(names);
    assertEquals(expectedValue, result.trim());
  }

  @Test
  void whenWriteUsingByteSink_thenWritten() throws IOException {
    final var expectedValue = "Hello world";
    ByteSink sink = Files.asByteSink(file);
    sink.write(expectedValue.getBytes());
    final var result = Files.toString(file, Charsets.UTF_8);
    assertEquals(expectedValue, result);
  }

  @Test
  void whenReadUsingFiles_thenRead() throws IOException {
    final var expectedValue = "Hello world";
    ByteSink sink = Files.asByteSink(file);
    sink.write(expectedValue.getBytes());
    final var result = Files.toString(file, Charsets.UTF_8);
    assertEquals(expectedValue, result);
  }

  @Test
  void whenReadMultipleLinesUsingFiles_thenRead() throws IOException {
    final var names = Lists.newArrayList("John", "Jane", "Adam", "Tom");
    final var sink = Files.asCharSink(file, Charsets.UTF_8);
    sink.writeLines(names, "\n");
    final var result = Files.readLines(file, Charsets.UTF_8);
    assertTrue(result.containsAll(List.of("John", "Jane", "Adam", "Tom")));
  }
}
