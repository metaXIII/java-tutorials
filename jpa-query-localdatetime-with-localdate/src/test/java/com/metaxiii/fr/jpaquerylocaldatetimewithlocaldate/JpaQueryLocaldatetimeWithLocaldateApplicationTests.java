package com.metaxiii.fr.jpaquerylocaldatetimewithlocaldate;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class JpaQueryLocaldatetimeWithLocaldateApplicationTests {

  private static final LocalDate date = LocalDate.of(2025, 10, 12);
  private static final LocalDateTime startOfDay = date.atStartOfDay();
  private static final LocalDateTime endOfDay = date.plusDays(1).atStartOfDay().minusSeconds(1);

  @Autowired
  private EventRepository eventRepository;

  @Autowired
  private EventCriteriaRepository eventCriteriaRepository;

  @Test
  void contextLoads() {
    assertDoesNotThrow(() -> JpaQueryLocaldatetimeWithLocaldateApplication.main(new String[] {}));
  }

  @Test
  void itShouldFindByCreatedAtBetween() {
    final var results = eventRepository.findByCreatedAtBetween(startOfDay, endOfDay);
    assertEquals(3, results.size());
  }

  @Test
  void itShouldFindByCreatedAtGreaterThanEqualAndCreatedAtLessThan() {
    final var results = eventRepository.findByCreatedAtGreaterThanEqualAndCreatedAtLessThan(startOfDay, endOfDay);
    assertEquals(3, results.size());
  }

  @Test
  void itShouldFindByCreatedDate() {
    final var results = eventCriteriaRepository.findByCreatedDate(date);
    assertEquals(3, results.size());
  }

  @Test
  void itShouldFindByDate() {
    final var events = eventRepository.findByDate(date);
    assertEquals(3, events.size());
  }

  @Test
  void itShouldFindByDateRangeNative() {
    final var results = eventRepository.findByDateRangeNative(startOfDay, endOfDay);
    assertEquals(3, results.size());
  }
}
