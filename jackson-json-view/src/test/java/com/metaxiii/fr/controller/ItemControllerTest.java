package com.metaxiii.fr.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.metaxiii.fr.model.Item;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

@SpringBootTest
@AutoConfigureMockMvc
class ItemControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Test
  void getItemInternal() throws Exception {
    final MvcResult mvcResult = mockMvc
      .perform(get("/items-internal").contentType(MediaType.APPLICATION_JSON))
      .andDo(print())
      .andExpect(status().isOk())
      .andReturn();
    final Item item = new ObjectMapper()
      .readValue(mvcResult.getResponse().getContentAsString(), Item.class);
    assertEquals(1, item.getId());
    assertEquals("item name", item.getItemName());
    assertEquals("owner", item.getOwnerName());
    final String string = new ObjectMapper().writeValueAsString(item);
    assertEquals(
      "{\"id\":1,\"itemName\":\"item name\",\"ownerName\":\"owner\"}",
      string
    );
  }

  @Test
  void getItemPublic() throws Exception {
    final MvcResult mvcResult = mockMvc
      .perform(get("/items-public").contentType(MediaType.APPLICATION_JSON))
      .andDo(print())
      .andExpect(status().isOk())
      .andReturn();
    final Item item = new ObjectMapper()
      .readValue(mvcResult.getResponse().getContentAsString(), Item.class);
    assertEquals(1, item.getId());
    assertEquals("item name", item.getItemName());
    Jackson2ObjectMapperBuilder mapperBuilder = new Jackson2ObjectMapperBuilder();
    final ObjectMapper mapper = mapperBuilder
      .build()
      .setSerializationInclusion(JsonInclude.Include.NON_NULL);
    final String string = mapper.writeValueAsString(item);
    assertEquals("{\"id\":1,\"itemName\":\"item name\"}", string);
  }
}
