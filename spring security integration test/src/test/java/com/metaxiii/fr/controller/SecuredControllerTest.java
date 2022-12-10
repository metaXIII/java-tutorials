package com.metaxiii.fr.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(SecuredController.class)
class SecuredControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Test
  @WithMockUser(value = "none")
  void givenAuthRequestOnPublicService_shouldSucceedWith200() throws Exception {
    mockMvc
      .perform(get("/public/hello").contentType(MediaType.APPLICATION_JSON))
      .andExpect(status().isOk());
  }

  @Test
  void givenRequestOnPrivateService_shouldFailWith401() throws Exception {
    mockMvc
      .perform(get("/private/hello").contentType(MediaType.APPLICATION_JSON))
      .andExpect(status().isUnauthorized());
  }

  @WithMockUser(value = "spring")
  @Test
  void givenAuthRequestOnPrivateService_shouldSucceedWith200()
    throws Exception {
    mockMvc
      .perform(get("/private/hello").contentType(MediaType.APPLICATION_JSON))
      .andExpect(status().isOk());
  }
}
