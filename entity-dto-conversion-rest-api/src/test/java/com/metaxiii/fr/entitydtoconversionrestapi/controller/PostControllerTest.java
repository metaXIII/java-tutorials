package com.metaxiii.fr.entitydtoconversionrestapi.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.metaxiii.fr.entitydtoconversionrestapi.config.Config;
import com.metaxiii.fr.entitydtoconversionrestapi.dto.PostDto;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.json.JsonMapper;

@Import(Config.class)
@AutoConfigureMockMvc
@Sql(scripts = "classpath:/init-db/post.sql")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PostControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Test
  void getPosts() throws Exception {
    final var url = "/0/1/asc/userName";
    final var mvcResult = mockMvc.perform(get(url)).andDo(print()).andExpect(status().isOk()).andReturn();
    final var objectMapper = new JsonMapper();
    Assertions.assertDoesNotThrow(() -> {
      final List<PostDto> result = objectMapper.readValue(
        mvcResult.getResponse().getContentAsString(),
        new TypeReference<>() {}
      );
      assertEquals(1, result.size());
    });
  }
}
