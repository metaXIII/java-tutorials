package com.metaxiii.fr.controller;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.apache.commons.lang3.RandomStringUtils.randomNumeric;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.metaxiii.fr.entity.Book;
import java.io.UnsupportedEncodingException;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class BookControllerTest {

  private static final String API_ROOT = "/api/books";

  @Autowired
  private MockMvc mockMvc;

  private static Book getBook(final MvcResult mvcResult)
    throws JsonProcessingException, UnsupportedEncodingException {
    return new ObjectMapper()
      .readValue(mvcResult.getResponse().getContentAsString(), Book.class);
  }

  public static String ObjectToJson(final Book obj) {
    try {
      return new ObjectMapper().writeValueAsString(obj);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @Test
  void whenGetAllBooks_thenOK() throws Exception {
    mockMvc
      .perform(get(API_ROOT).with(csrf()))
      .andDo(print())
      .andExpect(status().isOk());
  }

  @Test
  void whenInvalidBook_thenError() throws Exception {
    Book book = createRandomBook();
    book.setAuthor(null);
    mockMvc
      .perform(
        post(API_ROOT)
          .with(csrf())
          .content(ObjectToJson(book))
          .contentType(MediaType.APPLICATION_JSON)
      )
      .andDo(print())
      .andExpect(status().isBadRequest());
  }

  @Test
  void whenGetBooksByTitle_thenOK() throws Exception {
    Book book = createRandomBook();
    createBookAsUri(book);
    final MvcResult mvcResult = mockMvc
      .perform(get(API_ROOT + "/title/" + book.getTitle()).with(csrf()))
      .andDo(print())
      .andExpect(status().isOk())
      .andReturn();
    List<Book> bookReturn = new ObjectMapper()
      .readValue(
        mvcResult.getResponse().getContentAsString(),
        new TypeReference<>() {}
      );
    assertTrue(bookReturn.size() > 0);
  }

  @Test
  void whenGetCreatedBookById_thenOK() throws Exception {
    Book book = createRandomBook();
    String location = createBookAsUri(book);
    final MvcResult mvcResult = mockMvc
      .perform(get(API_ROOT + location).with(csrf()))
      .andDo(print())
      .andExpect(status().isOk())
      .andReturn();
    Book bookReturn = getBook(mvcResult);
    assertEquals(book.getTitle(), bookReturn.getTitle());
  }

  @Test
  void whenGetNotExistBookById_thenNotFound() throws Exception {
    mockMvc
      .perform(get(API_ROOT + "/" + randomNumeric(4)).with(csrf()))
      .andDo(print())
      .andExpect(status().isNotFound());
  }

  @Test
  void whenCreateNewBook_thenCreated() throws Exception {
    Book book = createRandomBook();
    mockMvc
      .perform(
        post(API_ROOT)
          .with(csrf())
          .content(ObjectToJson(book))
          .contentType(MediaType.APPLICATION_JSON)
      )
      .andDo(print())
      .andExpect(status().isCreated());
  }

  @Test
  void whenUpdateCreatedBook_thenUpdated() throws Exception {
    Book book = createRandomBook();
    String location = createBookAsUri(book);
    book.setId(Long.parseLong(location.substring(1)));
    book.setAuthor("newAuthor");
    mockMvc
      .perform(
        put(API_ROOT + location)
          .with(csrf())
          .content(ObjectToJson(book))
          .contentType(MediaType.APPLICATION_JSON)
      )
      .andDo(print())
      .andExpect(status().isOk());
    final MvcResult mvcResult = mockMvc
      .perform(get(API_ROOT + location).with(csrf()))
      .andDo(print())
      .andExpect(status().isOk())
      .andReturn();
    Book bookReturn = getBook(mvcResult);
    assertEquals("newAuthor", bookReturn.getAuthor());
  }

  @Test
  void whenDeleteCreatedBook_thenOk() throws Exception {
    Book book = createRandomBook();
    String location = createBookAsUri(book);
    mockMvc
      .perform(delete(API_ROOT + location).with(csrf()))
      .andDo(print())
      .andExpect(status().isOk());
    mockMvc
      .perform(get(API_ROOT + location))
      .andDo(print())
      .andExpect(status().isNotFound());
  }

  @Test
  void itshouldThrowsBookMismatchExceptionWhenUpdateHasDifferentBookIdAndId()
    throws Exception {
    Book book = createRandomBook();
    String location = createBookAsUri(book);
    book.setId(Long.valueOf(randomNumeric(6)));
    book.setAuthor("newAuthor");
    mockMvc
      .perform(
        put(API_ROOT + location)
          .with(csrf())
          .content(ObjectToJson(book))
          .contentType(MediaType.APPLICATION_JSON)
      )
      .andDo(print())
      .andExpect(status().isBadRequest());
  }

  private Book createRandomBook() {
    Book book = new Book();
    book.setTitle(randomAlphabetic(10));
    book.setAuthor(randomAlphabetic(15));
    return book;
  }

  private String createBookAsUri(Book book) throws Exception {
    final MvcResult mvcResult = mockMvc
      .perform(
        post(API_ROOT)
          .with(csrf())
          .content(ObjectToJson(book))
          .contentType(MediaType.APPLICATION_JSON)
      )
      .andDo(print())
      .andExpect(status().isCreated())
      .andReturn();
    Book bookReturn = getBook(mvcResult);
    return "/" + bookReturn.getId();
  }
}
