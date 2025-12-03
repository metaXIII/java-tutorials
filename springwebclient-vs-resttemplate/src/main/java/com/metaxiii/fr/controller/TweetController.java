package com.metaxiii.fr.controller;

import com.metaxiii.fr.model.Tweet;
import java.net.URI;
import java.time.Duration;
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
import reactor.core.publisher.Mono;

@Slf4j
@RestController
public class TweetController {

  private final ServerProperties serverProperties;

  public TweetController(final ServerProperties serverProperties) {
    this.serverProperties = serverProperties;
  }

  @GetMapping("/slow-service-tweets")
  public Mono<List<Tweet>> getAllTweets() {
    return Mono.just(
      Arrays.asList(
        new Tweet("RestTemplate rules", "@user1"),
        new Tweet("WebClient is better", "@user2"),
        new Tweet("OK, both are useful", "@user1")
      )
    ).delayElement(Duration.ofSeconds(1));
  }

  @GetMapping("/tweets-blocking")
  public Mono<List<Tweet>> getTweetsBlocking() {
    log.info("Starting BLOCKING Controller (Fixing with WebClient)!");
    return WebClient.create("http://localhost:" + serverProperties.getPort())
      .get()
      .uri("/slow-service-tweets")
      .retrieve()
      .bodyToMono(new ParameterizedTypeReference<List<Tweet>>() {})
      .doOnNext(result -> {
        result.forEach(tweet -> log.info(tweet.toString()));
        log.info("Exiting Controller!");
      });
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
