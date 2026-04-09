package ex.org.project.entityservice.repository;

import ex.org.project.entityservice.model.LookupCenter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LookupCenterRepository extends JpaRepository<LookupCenter, Integer> {}
