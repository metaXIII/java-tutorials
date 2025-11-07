package com.metaxiii.fr.jpaquerylocaldatetimewithlocaldate;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class EventCriteriaRepository {

  @PersistenceContext
  private EntityManager entityManager;

  public List<Event> findByCreatedDate(final LocalDate date) {
    final LocalDateTime startOfDay = date.atStartOfDay();
    final LocalDateTime endOfDay = date.plusDays(1).atStartOfDay();
    final CriteriaBuilder cb = entityManager.getCriteriaBuilder();
    final CriteriaQuery<Event> cq = cb.createQuery(Event.class);
    final Root<Event> root = cq.from(Event.class);
    cq.select(root).where(cb.between(root.get("createdAt"), startOfDay, endOfDay));
    return entityManager.createQuery(cq).getResultList();
  }
}
