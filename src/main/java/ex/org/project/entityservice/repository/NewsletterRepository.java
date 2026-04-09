package ex.org.project.entityservice.repository;

import ex.org.project.entityservice.model.Newsletter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface NewsletterRepository extends JpaRepository<Newsletter, Integer> {

    List<Newsletter> findAllByReleaseDateIsLessThanEqualOrderByReleaseDateDesc(LocalDate currentDate);
}
