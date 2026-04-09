package ex.org.project.entityservice.repository;

import ex.org.project.entityservice.model.StudyPropertyValue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudyPropertyValueRepository extends JpaRepository<StudyPropertyValue, Integer> {

   List<StudyPropertyValue> findByStudyIdAndPageOrderByDisplayOrder(Integer studyId, String page);

}

