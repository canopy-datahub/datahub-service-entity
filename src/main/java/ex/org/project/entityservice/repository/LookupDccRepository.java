package ex.org.project.entityservice.repository;

import ex.org.project.entityservice.model.LookupDcc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LookupDccRepository extends JpaRepository<LookupDcc, Integer> {}
