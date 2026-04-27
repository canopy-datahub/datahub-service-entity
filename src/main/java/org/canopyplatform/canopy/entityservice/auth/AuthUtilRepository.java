package org.canopyplatform.canopy.entityservice.auth;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class AuthUtilRepository {

    private final EntityManager entityManager;

    private static final String STUDY_ID_QUERY = "select vs.study_id from view_study vs join data_submission ds on vs.study_id = ds.study_id join data_file df on ds.id = df.submission_id where df.id in ?1";
    private static final String APPROVED_FILES_QUERY = "select df.id from data_file df join lkup_status ls on df.status_id=ls.id where ls.name='approved' and df.id in ?1";

    private static final String STUDYID_QUERY = "select vs.study_id from view_study vs where vs.study_id = ?1";


    public List<String> findStudyIdNumbersOfFilesIn(List<Integer> fileIds){
        Query query = entityManager.createNativeQuery(STUDY_ID_QUERY);
        query.setParameter(1, fileIds);
        return query.getResultList();
    }

    public List<Integer> findAllApprovedFileIdsIn(List<Integer> fileIds){
        Query query = entityManager.createNativeQuery(APPROVED_FILES_QUERY);
        query.setParameter(1, fileIds);
        return query.getResultList();
    }

    public String findStudyIdOfStudy(Integer studyId){
        Query query = entityManager.createNativeQuery(STUDYID_QUERY);
        query.setParameter(1, studyId);
        return (String) query.getSingleResult();
    }

}
