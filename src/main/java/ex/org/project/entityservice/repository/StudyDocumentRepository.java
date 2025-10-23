package ex.org.project.entityservice.repository;

import ex.org.project.entityservice.model.StudyDocument;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface StudyDocumentRepository extends JpaRepository<StudyDocument, Integer> {

    List<StudyDocument> findByStudyIdAndLookupDataFileCategoryCategoryGroupOrderByDisplayOrder(Integer studyId, String category);

    List<Long> findDocumentSizeByStudyIdIn(List<Integer> studyIds);

}

