package ex.org.project.entityservice.repository;

import ex.org.project.entityservice.model.LookupEntityType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LkupEntityType extends JpaRepository<LookupEntityType, Integer> {}
