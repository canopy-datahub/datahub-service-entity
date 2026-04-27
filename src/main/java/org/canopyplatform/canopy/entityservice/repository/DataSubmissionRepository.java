package org.canopyplatform.canopy.entityservice.repository;

import org.canopyplatform.canopy.entityservice.model.DataSubmission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface DataSubmissionRepository extends JpaRepository<DataSubmission, Integer> {
    @Query(nativeQuery = true,
    		value = "SELECT d.study_id, MAX(d.date_approved) FROM data_submission d "
    				+ "WHERE d.date_approved >= ?1 OR "
    				+ "d.study_id IN "
    				+ "(SELECT DISTINCT d2.study_id FROM data_submission d2 WHERE d2.date_approved IS NOT NULL FETCH FIRST 3 ROWS ONLY) "
    				+ "GROUP BY d.study_id "
    				+ "ORDER BY MAX(d.date_approved) DESC")
    List<Integer> findUniqueStudyIdsLast30DaysOrRecent3(@Param("thirtyDaysAgo") Date thirtyDaysAgo);


}
