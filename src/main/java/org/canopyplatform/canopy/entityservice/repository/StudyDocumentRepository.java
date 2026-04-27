package org.canopyplatform.canopy.entityservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import org.canopyplatform.canopy.entityservice.model.StudyDocument;


@Repository
public interface StudyDocumentRepository extends JpaRepository<StudyDocument, Integer> {

    List<StudyDocument> findByStudyIdAndLookupDataFileCategoryCategoryGroupOrderByDisplayOrder(Integer studyId, String category);

    List<Long> findDocumentSizeByStudyIdIn(List<Integer> studyIds);

}

