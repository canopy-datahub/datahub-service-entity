package ex.org.project.entityservice.repository;

import ex.org.project.entityservice.model.DataFile;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Repository
public interface DataFileRepository extends JpaRepository<DataFile, Integer> {

    List<DataFile> findDataFilesByDataSubmission_StudyIdAndLookupDataFileCategory_CategoryGroupAndStatus_NameAndStatus_Usage(Integer studyId, String categoryGroup, String name, String usage);

    List<DataFile> findByDataSubmission_StudyIdAndIsCurrentVersionTrueAndLookupDataFileCategoryCategoryGroup(Integer studyId, String category);
    
    List<DataFile> findAllByDataSubmission_StudyIdAndLookupDataFileCategory_CategoryGroupAndStatus_NameAndIsCurrentVersionTrue(Integer studyId, String categoryGroup, String status);

    @Query(value = "SELECT dcc as name, " +
            "COUNT(DISTINCT study_phs) AS study_count, " +
            "SUM(data_file_count) AS data_file_count, " +
            "SUM(total_file_count) - SUM(data_file_count) AS document_count, " +
            "TRUNC(SUM(COALESCE(total_file_size,0)),2) AS total_file_size " +
            "FROM view_current_hub_content " +
            "where study_status='Approved' " +
            "GROUP BY dcc",
            nativeQuery = true)
    List<Map<String, Object>> getDccStats();

    @Query(value = "select count(distinct s.id) from public.study s " +
            "JOIN public.lkup_status st ON s.status_id = st.id " +
            "where st.name = 'Approved' and st.usage = 'study'",
            nativeQuery = true)
    BigInteger getTotalStudies();

    @Query(value = "select count(distinct df.id) from public.data_file df " +
            "JOIN public.lkup_status st ON df.status_id = st.id " +
            "where st.name = 'approved' and st.usage = 'file'",
            nativeQuery = true)
    BigInteger getTotalFiles();


    @Query(value = "select ds.study_id, vs.title, vs.dcc, count(distinct df.id) as files, ds.date_approved as date " +
            "FROM public.data_file df left join " +
            "public.data_submission ds on df.submission_id = ds.id left join " +
            "public.view_study vs on ds.study_id = vs.study_id " +
            "where df.approval_date > :timePeriod and df.version_no = :versionNo and ds.date_approved is not null " +
            "group by ds.study_id, vs.title, vs.dcc, ds.date_approved order by ds.date_approved desc", nativeQuery = true)
    List<Map<String, Object>> findRecentNewFiles(@Param("versionNo") int versionNo, @Param("timePeriod") LocalDateTime timePeriod);

    @Query(value = "select ds.study_id, vs.title, vs.dcc, count(distinct df.id) as files, ds.date_approved as date " +
            "FROM public.data_file df left join " +
            "public.data_submission ds on df.submission_id = ds.id left join " +
            "public.view_study vs on ds.study_id = vs.study_id " +
            "where df.version_no = 1 and ds.date_approved is not null " +
            "group by ds.study_id, vs.title, vs.dcc, ds.date_approved " +
            "order by ds.date_approved desc " +
            "limit 3", nativeQuery = true)
    List<Map<String, Object>> findLatestNewFiles();


    @Query(value = "select ds.study_id, vs.title, vs.dcc, count(distinct df.id) as files, ds.date_approved as date " +
            "FROM public.data_file df left join " +
            "public.data_submission ds on df.submission_id = ds.id left join " +
            "public.view_study vs on ds.study_id = vs.study_id " +
            "where df.approval_date > :timePeriod and df.version_no > :versionNo and ds.date_approved is not null " +
            "group by ds.study_id, vs.title, vs.dcc, ds.date_approved order by ds.date_approved desc", nativeQuery = true)
    List<Map<String, Object>> findRecentUpdatedFiles(@Param("versionNo") int versionNo, @Param("timePeriod") LocalDateTime timePeriod);

    @Query(value = "select ds.study_id, vs.title, vs.dcc, count(distinct df.id) as files, ds.date_approved as date " +
            "FROM public.data_file df left join " +
            "public.data_submission ds on df.submission_id = ds.id left join " +
            "public.view_study vs on ds.study_id = vs.study_id " +
            "where df.version_no > 1 and ds.date_approved is not null " +
            "group by ds.study_id, vs.title, vs.dcc, ds.date_approved " +
            "order by ds.date_approved desc " +
            "limit 3", nativeQuery = true)
    List<Map<String, Object>> findLatestUpdatedFiles();

    @Query(value = "select exists (select sdf.* from sas_data_file sdf where sdf.parent_data_file_id=:dataFileId)",
        nativeQuery = true)
    Boolean existsSasFileForDataFile(Integer dataFileId);
}
