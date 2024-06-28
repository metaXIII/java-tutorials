package com.metaxiii.fr.avoidnomultipartboundarywasfound;

import static com.metaxiii.fr.avoidnomultipartboundarywasfound.FileController.FILES;
import static okhttp3.MediaType.parse;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class FileControllerTest {

  private static final String HOST = "http://localhost:";
  private static final String BOUNDARY = "OurCustomBoundaryValue";

  private static final String BODY =
    "--" +
    BOUNDARY +
    "\r\n" +
    "Content-Disposition: form-data; name=\"file\"; filename=\"import.csv\"\r\n" +
    "Content-Type: text/csv\r\n" +
    "\r\n" +
    "content-of-the-csv-file\r\n" +
    "--" +
    BOUNDARY +
    "\r\n" +
    "Content-Disposition: form-data; name=\"fileDescription\"\r\n" +
    "\r\n" +
    "Records\r\n" +
    "--" +
    BOUNDARY +
    "--";

  @LocalServerPort
  private int port;

  @Test
  void givenFormData_whenPostWithBoundary_thenReturn200() throws IOException {
    final RequestBody requestBody = RequestBody.create(
      BODY.getBytes(),
      parse(MediaType.MULTIPART_FORM_DATA_VALUE + "; boundary=" + BOUNDARY)
    );
    try (final Response response = executeCall(requestBody)) {
      assertEquals(HttpStatus.OK.value(), response.code());
    }
  }

  private Response executeCall(final RequestBody requestBody) throws IOException {
    final Request request = new Request.Builder().url(HOST + port + FILES).post(requestBody).build();
    return new OkHttpClient().newCall(request).execute();
  }

  @Test
  void givenFormData_whenPostWithoutBoundary_thenReturn500() throws IOException {
    final RequestBody requestBody = RequestBody.create(BODY.getBytes(), parse(MediaType.MULTIPART_FORM_DATA_VALUE));
    try (final Response response = executeCall(requestBody)) {
      assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), response.code());
    }
  }
}
