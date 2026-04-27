package org.canopyplatform.canopy.entityservice.repository;

import org.canopyplatform.canopy.entityservice.model.StudyPropertyValue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudyPropertyValueRepository extends JpaRepository<StudyPropertyValue, Integer> {

   List<StudyPropertyValue> findByStudyIdAndPageOrderByDisplayOrder(Integer studyId, String page);

}

