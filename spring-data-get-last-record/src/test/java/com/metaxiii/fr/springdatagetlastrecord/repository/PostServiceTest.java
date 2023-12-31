package com.metaxiii.fr.springdatagetlastrecord.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.metaxiii.fr.springdatagetlastrecord.entity.Post;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

@DataJpaTest
@Sql(scripts = "classpath:/data.sql")
class PostServiceTest {

  @Autowired
  private PostRepository repository;

  @Test
  void givenPosts_whenUsingQueryAnnotation_thenReturnLastPost() {
    Post post = repository.findLastPost();
    assertNotNull(post);
    assertEquals(5, post.getId());
  }

  @Test
  void itShouldFindFirstByOrderByPublicationDateDesc() {
    Post post = repository.findFirstByOrderByPublicationDateDesc();
    assertNotNull(post);
    assertEquals(5, post.getId());
  }

  @Test
  void itShouldFindTopByOrderByPublicationDateDesc() {
    Post post = repository.findTopByOrderByPublicationDateDesc();
    assertNotNull(post);
    assertEquals(5, post.getId());
  }
}
