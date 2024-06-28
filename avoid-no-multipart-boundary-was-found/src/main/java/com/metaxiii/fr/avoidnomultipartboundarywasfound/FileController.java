package com.metaxiii.fr.avoidnomultipartboundarywasfound;

import static com.metaxiii.fr.avoidnomultipartboundarywasfound.FileController.FILES;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@Controller
@RequestMapping(FILES)
public class FileController {

  protected static final String FILES = "/files";

  @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  public String upload(@RequestParam("file") final MultipartFile file, final String fileDescription) {
    log.info("Uploading file {}", file.getName());
    log.info("upload fileDescrition {}", fileDescription);
    return "files/success";
  }
}
