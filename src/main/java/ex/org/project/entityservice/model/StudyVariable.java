package ex.org.project.entityservice.model;

//needed to use projection since the view_study_variables table has no primary key
public interface StudyVariable {

    Integer getStudyId();

    String getStudyPhs();

    Integer getVariableId();

    String getVariable();

    Boolean getIsTier1Variable();

    String getVariableLabel();

}
