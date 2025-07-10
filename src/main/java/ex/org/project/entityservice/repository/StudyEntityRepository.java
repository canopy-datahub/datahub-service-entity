package ex.org.project.entityservice.repository;

import ex.org.project.entityservice.model.Study;
import ex.org.project.entityservice.model.StudyVariable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StudyEntityRepository extends JpaRepository<Study, Integer> {

    @Query(nativeQuery = true, value =
                                   "select vsv.study_id as studyId, vsv.study_phs as studyPhs, vsv.variable_id as variableId, " +
                                   "vsv.variable as variable, vsv.is_tier1_variable as isTier1Variable, vsv.variable_label as variableLabel " +
                                   "from public.view_study_variables vsv where study_id = :studyId")
    List<StudyVariable> findAllVariablesByStudyId(Integer studyId);

}
