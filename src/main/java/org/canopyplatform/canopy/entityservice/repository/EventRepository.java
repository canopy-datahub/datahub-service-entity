package org.canopyplatform.canopy.entityservice.repository;

import org.springframework.data.domain.Limit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.canopyplatform.canopy.entityservice.model.Event;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Integer> {
    List<Event> findEventsByEventDateAfterOrderByEventDateAsc(LocalDateTime currentDateTime, Limit limit); //future events
    List<Event> findAllByEventDateBeforeOrderByEventDateDesc(LocalDateTime currentDateTime);
}
