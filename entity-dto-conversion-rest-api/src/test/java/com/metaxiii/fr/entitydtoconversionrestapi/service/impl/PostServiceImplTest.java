package com.metaxiii.fr.entitydtoconversionrestapi.service.impl;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.metaxiii.fr.entitydtoconversionrestapi.model.Post;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.jdbc.Sql;

@DataJpaTest
@ComponentScan("com.metaxiii.fr.entitydtoconversionrestapi")
@Sql(scripts = "classpath:/init-db/post.sql")
class PostServiceImplTest {

  @Autowired
  private PostServiceImpl postService;

  @Test
  void getPostsList() {
    assertDoesNotThrow(() -> {
      final List<Post> postsList = postService.getPostsList(0, 1, "asc", "userName");
      assertEquals(1, postsList.size());
    });
  }
}
