package ex.org.project.entityservice.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import ex.org.project.entityservice.model.StudyView;

@Repository
public interface StudyRepository extends JpaRepository<StudyView, Integer> {

    List<StudyView> findByIdIn(List<Integer> studyIds);

    @Query(nativeQuery = true,
            value = "SELECT vs.study_id, vs.title, vs.dcc, vs.created_at, vs.release_date,vs.description, vs.phs, vs.status FROM public.view_study vs " +
                    "WHERE vs.status='Approved' AND to_timestamp(vs.release_date,'MM/DD/YYYY') >:timePeriod " +
                    "ORDER BY to_timestamp(vs.release_date,'MM/DD/YYYY') DESC ")
    List <StudyView> findRecentlyApprovedStudies(LocalDateTime timePeriod);

    @Query(nativeQuery = true,
            value = "SELECT vs.study_id, vs.title, vs.dcc, vs.created_at, vs.release_date, " +
                    "vs.description, vs.phs, vs.status FROM public.view_study vs " +
                    "WHERE vs.status = 'Approved' " +
                    "ORDER BY to_timestamp(vs.release_date,'MM/DD/YYYY') DESC FETCH FIRST 3 ROWS ONLY ")
    List <StudyView> findFirstThreeRecentlyApprovedStudies();

    @Query(nativeQuery = true,
            value = "select vs.* from view_study vs where study_id in (" +
                    "select study_id from view_study_variables vsv where vsv.variable_id='1') " +
                    "and vs.status = 'Approved' order by vs.phs")
    List<StudyView> findAllByVariableId(Integer variableId);

}