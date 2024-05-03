package com.metaxiii.fr;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.metaxiii.fr.model.GenericBuilder;
import com.metaxiii.fr.model.GenericPost;
import com.metaxiii.fr.model.LombokPost;
import com.metaxiii.fr.model.PersonalPost;
import com.metaxiii.fr.model.Post;
import org.junit.jupiter.api.Test;

class PostTest {

  private static final String TITLE = "Java builder pattern";
  private static final String TEXT = "Explaining how to implement the Builder Pattern in Java";
  private static final String CATEGORY = "Programming";

  @Test
  void itShouldCreatePost() {
    final var post = new Post.Builder().title(TITLE).text(TEXT).category(CATEGORY).build();
    assertEquals(TITLE, post.getTitle());
    assertEquals(TEXT, post.getText());
    assertEquals(CATEGORY, post.getCategory());
  }

  @Test
  void itShouldCreatePostWithGenericBuilder() {
    final var post = GenericBuilder
      .of(GenericPost::new)
      .with(GenericPost::setTitle, TITLE)
      .with(GenericPost::setText, TEXT)
      .with(GenericPost::setCategory, CATEGORY)
      .build();
    assertEquals(TITLE, post.getTitle());
    assertEquals(TEXT, post.getText());
    assertEquals(CATEGORY, post.getCategory());
  }

  @Test
  void itShouldCreatePostWithLombok() {
    final var post = LombokPost.builder().title(TITLE).text(TEXT).category(CATEGORY).build();
    assertEquals(TITLE, post.getTitle());
    assertEquals(TEXT, post.getText());
    assertEquals(CATEGORY, post.getCategory());
  }

  @Test
  void itShouldCreatePostWithPersonal() {
    final var post = PersonalPost.builder().title(TITLE).text(TEXT).category(CATEGORY).build();
    assertEquals(TITLE, post.getTitle());
    assertEquals(TEXT, post.getText());
    assertEquals(CATEGORY, post.getCategory());
  }

  @Test
  void itShouldCreatePostWithPersonalAndCustomValidators() {
    assertThrows(NullPointerException.class, () -> getPersonalPost(false, false, false));
    assertThrows(NullPointerException.class, () -> getPersonalPost(false, true, true));
    assertThrows(NullPointerException.class, () -> getPersonalPost(true, false, true));
    assertThrows(NullPointerException.class, () -> getPersonalPost(true, true, false));
  }

  private static void getPersonalPost(final boolean title, final boolean text, final boolean category)
    throws IllegalAccessException {
    if (!title && !text && !category) {
      PersonalPost.builder().build();
    }
    if (!title && text && category) {
      PersonalPost.builder().text(TEXT).category(CATEGORY).build();
    }
    if (title && !text && category) {
      PersonalPost.builder().title(TITLE).category(CATEGORY).build();
    }
    if (title && text && !category) {
      PersonalPost.builder().title(TITLE).text(TEXT).build();
    }
    throw new IllegalAccessException("You should not be in this situation");
  }
}
