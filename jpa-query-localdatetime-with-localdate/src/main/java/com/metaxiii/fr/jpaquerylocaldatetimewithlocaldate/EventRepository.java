package com.metaxiii.fr.jpaquerylocaldatetimewithlocaldate;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface EventRepository extends JpaRepository<Event, Long> {
  List<Event> findByCreatedAtBetween(LocalDateTime start, LocalDateTime end);

  List<Event> findByCreatedAtGreaterThanEqualAndCreatedAtLessThan(LocalDateTime start, LocalDateTime end);

  @Query("SELECT e FROM Event e WHERE cast(e.createdAt as date ) = :date")
  List<Event> findByDate(@Param("date") LocalDate date);

  @Query(
    value = """
    SELECT *
    FROM events e
    WHERE e.created_at >= :startOfDay
      AND e.created_at < :endOfDay""",
    nativeQuery = true
  )
  List<Event> findByDateRangeNative(
    @Param("startOfDay") LocalDateTime startOfDay,
    @Param("endOfDay") LocalDateTime endOfDay
  );
}
