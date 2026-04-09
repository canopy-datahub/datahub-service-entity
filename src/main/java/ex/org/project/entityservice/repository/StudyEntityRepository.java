package ex.org.project.entityservice.repository;

import ex.org.project.entityservice.model.Study;
import ex.org.project.entityservice.model.StudyVariable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StudyEntityRepository extends JpaRepository<Study, Integer> {

    @Query(nativeQuery = true, value =
                                   "select vv.study_id as studyId, vv.variable_id as variableId, " +
                                   "vv.variable as variable, vv.is_tier1_variable as isTier1Variable, vv.variable_label as variableLabel " +
                                   "from public.view_variables vv where study_id = :studyId")
    List<StudyVariable> findAllVariablesByStudyId(Integer studyId);

}
