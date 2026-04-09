package ex.org.project.entityservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ex.org.project.entityservice.model.StudyDocument;


@Repository
public interface StudyDocumentRepository extends JpaRepository<StudyDocument, Integer> {

    List<StudyDocument> findByStudyIdAndLookupDataFileCategoryCategoryGroupOrderByDisplayOrder(Integer studyId, String category);

    List<Long> findDocumentSizeByStudyIdIn(List<Integer> studyIds);

}

