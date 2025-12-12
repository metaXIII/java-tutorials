package com.metaxiii.fr.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.metaxiii.fr.entity.Book;
import java.io.UnsupportedEncodingException;
import java.util.List;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.json.JsonMapper;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BookControllerTest {

  private static final String API_ROOT = "/api/books";

  @Autowired
  private MockMvc mockMvc;

  @Test
  void itshouldThrowsBookMismatchExceptionWhenUpdateHasDifferentBookIdAndId() throws Exception {
    final Book book = createRandomBook();
    final String location = createBookAsUri(book);
    book.setId(Long.valueOf(RandomStringUtils.secure().nextNumeric(6)));
    book.setAuthor("newAuthor");
    mockMvc
      .perform(
        put(API_ROOT + location).with(csrf()).content(ObjectToJson(book)).contentType(MediaType.APPLICATION_JSON)
      )
      .andDo(print())
      .andExpect(status().isBadRequest());
  }

  @Test
  void whenCreateNewBook_thenCreated() throws Exception {
    final Book book = createRandomBook();
    mockMvc
      .perform(post(API_ROOT).with(csrf()).content(ObjectToJson(book)).contentType(MediaType.APPLICATION_JSON))
      .andDo(print())
      .andExpect(status().isCreated());
  }

  @Test
  void whenDeleteCreatedBook_thenOk() throws Exception {
    final Book book = createRandomBook();
    final String location = createBookAsUri(book);
    mockMvc.perform(delete(API_ROOT + location).with(csrf())).andDo(print()).andExpect(status().isOk());
    mockMvc.perform(get(API_ROOT + location)).andDo(print()).andExpect(status().isNotFound());
  }

  @Test
  void whenGetAllBooks_thenOK() throws Exception {
    mockMvc.perform(get(API_ROOT).with(csrf())).andDo(print()).andExpect(status().isOk());
  }

  @Test
  void whenGetBooksByTitle_thenOK() throws Exception {
    final Book book = createRandomBook();
    createBookAsUri(book);
    final MvcResult mvcResult = mockMvc
      .perform(get(API_ROOT + "/title/" + book.getTitle()).with(csrf()))
      .andDo(print())
      .andExpect(status().isOk())
      .andReturn();
    final List<Book> bookReturn = new JsonMapper().readValue(
      mvcResult.getResponse().getContentAsString(),
      new TypeReference<>() {}
    );
    assertFalse(bookReturn.isEmpty());
  }

  private String createBookAsUri(final Book book) throws Exception {
    final MvcResult mvcResult = mockMvc
      .perform(post(API_ROOT).with(csrf()).content(ObjectToJson(book)).contentType(MediaType.APPLICATION_JSON))
      .andDo(print())
      .andExpect(status().isCreated())
      .andReturn();
    final Book bookReturn = getBook(mvcResult);
    return "/" + bookReturn.getId();
  }

  private static Book getBook(final MvcResult mvcResult) throws UnsupportedEncodingException {
    return new JsonMapper().readValue(mvcResult.getResponse().getContentAsString(), Book.class);
  }

  @Test
  void whenGetCreatedBookById_thenOK() throws Exception {
    final Book book = createRandomBook();
    final String location = createBookAsUri(book);
    final MvcResult mvcResult = mockMvc
      .perform(get(API_ROOT + location).with(csrf()))
      .andDo(print())
      .andExpect(status().isOk())
      .andReturn();
    final Book bookReturn = getBook(mvcResult);
    assertEquals(book.getTitle(), bookReturn.getTitle());
  }

  @Test
  void whenGetNotExistBookById_thenNotFound() throws Exception {
    mockMvc
      .perform(get(API_ROOT + "/" + RandomStringUtils.secure().nextNumeric(4)).with(csrf()))
      .andDo(print())
      .andExpect(status().isNotFound());
  }

  @Test
  void whenInvalidBook_thenError() throws Exception {
    final Book book = createRandomBook();
    book.setAuthor(null);
    mockMvc
      .perform(post(API_ROOT).with(csrf()).content(ObjectToJson(book)).contentType(MediaType.APPLICATION_JSON))
      .andDo(print())
      .andExpect(status().isBadRequest());
  }

  private Book createRandomBook() {
    final Book book = new Book();
    book.setTitle(RandomStringUtils.secure().nextNumeric(10));
    book.setAuthor(RandomStringUtils.secure().nextNumeric(15));
    return book;
  }

  public static String ObjectToJson(final Book obj) {
    try {
      return new JsonMapper().writeValueAsString(obj);
    } catch (final Exception e) {
      throw new RuntimeException(e);
    }
  }

  @Test
  void whenUpdateCreatedBook_thenUpdated() throws Exception {
    final Book book = createRandomBook();
    final String location = createBookAsUri(book);
    book.setId(Long.parseLong(location.substring(1)));
    book.setAuthor("newAuthor");
    mockMvc
      .perform(
        put(API_ROOT + location).with(csrf()).content(ObjectToJson(book)).contentType(MediaType.APPLICATION_JSON)
      )
      .andDo(print())
      .andExpect(status().isOk());
    final MvcResult mvcResult = mockMvc
      .perform(get(API_ROOT + location).with(csrf()))
      .andDo(print())
      .andExpect(status().isOk())
      .andReturn();
    final Book bookReturn = getBook(mvcResult);
    assertEquals("newAuthor", bookReturn.getAuthor());
  }
}
