package com.metaxiii.fr.controller;

import com.metaxiii.fr.model.Tweet;
import java.net.URI;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.server.autoconfigure.ServerProperties;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

@Slf4j
@RestController
public class TweetController {

  private final ServerProperties serverProperties;

  public TweetController(final ServerProperties serverProperties) {
    this.serverProperties = serverProperties;
  }

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
    final RestTemplate restTemplate = new RestTemplate();
    final ResponseEntity<List<Tweet>> response = restTemplate.exchange(
      URI.create("http://localhost:" + serverProperties.getPort() + "/slow-service-tweets"),
      HttpMethod.GET,
      null,
      new ParameterizedTypeReference<>() {}
    );
    final List<Tweet> result = Objects.requireNonNull(response.getBody());
    result.forEach(tweet -> log.info(tweet.toString()));
    log.info("Exiting BLOCKING Controller!");
    return result;
  }

  @GetMapping(value = "/tweets-non-blocking", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
  public Flux<Tweet> getTweetsNonBlocking() {
    log.info("Starting NON-BLOCKING Controller!");
    final Flux<Tweet> tweetFlux = WebClient.create()
      .get()
      .uri(URI.create("http://localhost:" + serverProperties.getPort() + "/slow-service-tweets"))
      .retrieve()
      .bodyToFlux(Tweet.class);
    //noinspection CallingSubscribeInNonBlockingScope
    tweetFlux.subscribe(tweet -> log.info(tweet.toString()));
    log.info("Exiting NON-BLOCKING Controller!");
    return tweetFlux;
  }
}
