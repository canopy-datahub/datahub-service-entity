package ex.org.project.entityservice.repository;

import ex.org.project.entityservice.model.Funding;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface FundingRepository extends JpaRepository <Funding, Long> {

    List<Funding> findByExpirationDateGreaterThanEqualOrderByStartDateDesc(LocalDateTime localDateTime);

    List<Funding> findAllByOrderByStartDateDesc();
    Optional<Funding> findBySlug(String slug);
}
