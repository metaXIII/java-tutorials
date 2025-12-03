package com.metaxiii.fr.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.metaxiii.fr.model.Tweet;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webtestclient.autoconfigure.AutoConfigureWebTestClient;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

@AutoConfigureWebTestClient
@ActiveProfiles(value = "test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class TweetControllerTest {

  @Autowired
  private WebTestClient webTestClient;

  @Test
  void getAllTweets() {
    webTestClient
      .get()
      .uri("/slow-service-tweets")
      .exchange()
      .expectStatus()
      .is2xxSuccessful()
      .expectBodyList(Tweet.class)
      .value(tweets -> assertEquals(3, tweets.size()));
  }

  @Test
  void getTweetsBlocking() {
    webTestClient.get().uri("/tweets-blocking").exchange().expectStatus().is2xxSuccessful().expectBodyList(Tweet.class);
  }

  @Test
  void getTweetsNonBlocking() {
    webTestClient
      .get()
      .uri("/tweets-non-blocking")
      .exchange()
      .expectStatus()
      .is2xxSuccessful()
      .expectBodyList(Tweet.class);
  }
}
