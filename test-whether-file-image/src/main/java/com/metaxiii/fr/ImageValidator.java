package com.metaxiii.fr;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import javax.imageio.ImageIO;
import org.apache.tika.Tika;

public class ImageValidator {

  private ImageValidator() {
    throw new IllegalStateException("Utility class");
  }

  public static boolean isImageFileUsingTika(final File file) throws IOException {
    final Tika tika = new Tika();
    final String mimeType = tika.detect(file);
    return mimeType.startsWith("image/");
  }

  public static boolean isImageFileUsingImageIO(final File file) throws IOException {
    final BufferedImage image = ImageIO.read(file);
    return image != null;
  }

  public static boolean isImageFileUsingProbeContentType(final File file) throws IOException {
    final Path filePath = file.toPath();
    final String mimeType = Files.probeContentType(filePath);
    return mimeType != null && mimeType.startsWith("image/");
  }
}
