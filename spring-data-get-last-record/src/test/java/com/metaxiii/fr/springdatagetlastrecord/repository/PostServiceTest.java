package com.metaxiii.fr.springdatagetlastrecord.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.metaxiii.fr.springdatagetlastrecord.entity.Post;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

@DataJpaTest
@Sql(scripts = "classpath:/data.sql")
class PostServiceTest {

  @Autowired
  private PostRepository repository;

  @Test
  void givenPosts_whenUsingQueryAnnotation_thenReturnLastPost() {
    final Post post = repository.findLastPost();
    assertNotNull(post);
    assertEquals(5, post.getId());
  }

  @Test
  void itShouldFindFirstByOrderByPublicationDateDesc() {
    final Post post = repository.findFirstByOrderByPublicationDateDesc();
    assertNotNull(post);
    assertEquals(5, post.getId());
  }

  @Test
  void itShouldFindTopByOrderByPublicationDateDesc() {
    final Post post = repository.findTopByOrderByPublicationDateDesc();
    assertNotNull(post);
    assertEquals(5, post.getId());
  }
}
