package com.metaxiii.fr;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.IOException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AppTest {

  private String absolutePath;

  @BeforeEach
  void beforeEach() throws Exception {
    final var url = getClass().getResource("/test.html");
    assertNotNull(url);
    final var file = new File(url.toURI());
    assertTrue(file.exists());
    absolutePath = file.getAbsolutePath();
  }

  // Test work but not passing on github actions
  // @Test
  // void givenHtmlFile_whenUsingDesktopClass_thenOpenFileInDefaultBrowser()
  // throws IOException {
  // final var htmlFile = new File(absolutePath);
  // Desktop.getDesktop().browse(htmlFile.toURI());
  // assertTrue(true);
  // }

  @Test
  void givenHtmlFile_whenUsingProcessBuilder_thenOpenFileInDefaultBrowser() throws IOException {
    final ProcessBuilder pb;
    if (System.getProperty("os.name").toLowerCase().contains("win")) {
      pb = new ProcessBuilder("cmd.exe", "/c", "start", absolutePath);
    } else {
      pb = new ProcessBuilder("xdg-open", absolutePath);
    }
    pb.start();
    assertTrue(true);
  }
}
