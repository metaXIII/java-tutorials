package com.metaxiii.fr.controller;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SecuredControllerSpringBootIntegrationTest {

  @Autowired
  private WebApplicationContext context;

  @Autowired
  private MockMvc mvc;

  @Test
  @WithMockUser("spring")
  void givenAuthRequestOnPrivateService_shouldSucceedWith200() throws Exception {
    mvc.perform(get("/private/hello").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
  }

  @Test
  @WithMockUser(roles = "NO_ROLE")
  void givenAuthRequestOnPrivateService_shouldSucceedWith401() throws Exception {
    mvc.perform(get("/private/hello").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isForbidden());
  }

  @BeforeEach
  void setup() {
    mvc = MockMvcBuilders.webAppContextSetup(context).apply(springSecurity()).build();
  }
}
