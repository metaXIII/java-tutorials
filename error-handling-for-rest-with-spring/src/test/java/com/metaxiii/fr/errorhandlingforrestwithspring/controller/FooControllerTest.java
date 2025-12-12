package com.metaxiii.fr.errorhandlingforrestwithspring.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class FooControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Test
  void findFoo() throws Exception {
    mockMvc.perform(get("/")).andDo(print()).andExpect(status().isOk());
  }

  @Test
  void findFooThrowsException() throws Exception {
    mockMvc.perform(get("/path")).andDo(print()).andExpect(status().isOk());
  }

  @Test
  void findFooThrowsAccessException() throws Exception {
    mockMvc.perform(get("/other")).andDo(print()).andExpect(status().isForbidden());
  }
}
