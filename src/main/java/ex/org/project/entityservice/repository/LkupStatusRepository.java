package ex.org.project.entityservice.repository;

import ex.org.project.entityservice.model.LkupStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LkupStatusRepository extends JpaRepository<LkupStatus, Integer> {
    LkupStatus findByUsageAndName(String usage, String name);

    LkupStatus findByName(String name);
}