package com.metaxiii.fr.controller;

import com.metaxiii.fr.model.Tweet;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@DirtiesContext
@AutoConfigureWebTestClient
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
        webTestClient.get()
                .uri("/tweets-blocking")
                .exchange()
                .expectStatus()
                .is2xxSuccessful()
                .expectBodyList(Tweet.class);
    }

    @Test
    void getTweetsNonBlocking() {
        webTestClient.get()
                .uri("/tweets-non-blocking")
                .exchange()
                .expectStatus()
                .is2xxSuccessful()
                .expectBodyList(Tweet.class);
    }
}
