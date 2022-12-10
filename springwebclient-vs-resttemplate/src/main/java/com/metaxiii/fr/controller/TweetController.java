package com.metaxiii.fr.controller;

import com.metaxiii.fr.model.Tweet;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

@RestController
@Slf4j
public class TweetController {

  private static final String URL = "http://localhost:8080/slow-service-tweets";

  @GetMapping("/slow-service-tweets")
  public List<Tweet> getAllTweets() throws InterruptedException {
    Thread.sleep(2000L); // delay
    return Arrays.asList(
      new Tweet("RestTemplate rules", "@user1"),
      new Tweet("WebClient is better", "@user2"),
      new Tweet("OK, both are useful", "@user1")
    );
  }

  @GetMapping("/tweets-blocking")
  public List<Tweet> getTweetsBlocking() {
    log.info("Starting BLOCKING Controller!");
    RestTemplate restTemplate = new RestTemplate();
    ResponseEntity<List<Tweet>> response = restTemplate.exchange(
      URL,
      HttpMethod.GET,
      null,
      new ParameterizedTypeReference<>() {}
    );
    final List<Tweet> result = Objects.requireNonNull(response.getBody());
    result.forEach(tweet -> log.info(tweet.toString()));
    log.info("Exiting BLOCKING Controller!");
    return result;
  }

  @GetMapping(
    value = "/tweets-non-blocking",
    produces = MediaType.TEXT_EVENT_STREAM_VALUE
  )
  public Flux<Tweet> getTweetsNonBlocking() {
    log.info("Starting NON-BLOCKING Controller!");
    Flux<Tweet> tweetFlux = WebClient
      .create()
      .get()
      .uri(URL)
      .retrieve()
      .bodyToFlux(Tweet.class);
    //noinspection CallingSubscribeInNonBlockingScope
    tweetFlux.subscribe(tweet -> log.info(tweet.toString()));
    log.info("Exiting NON-BLOCKING Controller!");
    return tweetFlux;
  }
}
