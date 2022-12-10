package com.metaxiii.fr.actuator;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@SpringBootTest
@AutoConfigureMockMvc
class ActuatorApplicationTests {

  @Autowired
  private MockMvc mockMvc;

  @Test
  void contextLoads() {
    Assertions.assertDoesNotThrow(() ->
      ActuatorApplication.main(new String[] {})
    );
  }

  @Test
  void itShouldCallActuator() throws Exception {
    mockMvc
      .perform(MockMvcRequestBuilders.get("/actuator"))
      .andDo(print())
      .andExpect(status().isOk());
    mockMvc
      .perform(MockMvcRequestBuilders.get("/actuator/info"))
      .andDo(print())
      .andExpect(status().isOk());
  }
}
