package ex.org.project.entityservice.repository;

import ex.org.project.entityservice.model.LkupPropertyCodelistValue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LkupPropertyCodelistValueRepository extends JpaRepository<LkupPropertyCodelistValue, Integer> {

    List<LkupPropertyCodelistValue> findAllByPropertyCodelist_IdIn(List<Integer> codelistIds);

}
