package ex.org.project.entityservice.repository;

import ex.org.project.entityservice.model.LkupPropertySource;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LkupPropertySourceRepository extends JpaRepository<LkupPropertySource, Integer> {

 List<LkupPropertySource> findAllByNameIn(List<String> sourceNames);

}
