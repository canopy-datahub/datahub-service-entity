package ex.org.project.entityservice.repository;

import ex.org.project.entityservice.model.Event;
import org.springframework.data.domain.Limit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Integer> {
    List<Event> findEventsByEventDateAfterOrderByEventDateAsc(LocalDateTime currentDateTime, Limit limit); //future events
    List<Event> findAllByEventDateBeforeOrderByEventDateDesc(LocalDateTime currentDateTime);
}
