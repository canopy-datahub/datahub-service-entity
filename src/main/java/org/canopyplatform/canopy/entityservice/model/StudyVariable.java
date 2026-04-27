package org.canopyplatform.canopy.entityservice.model;

//needed to use projection since the view_study_variables table has no primary key
public interface StudyVariable {

    Integer getStudyId();

    Integer getVariableId();

    String getVariable();

    Boolean getIsTier1Variable();

    String getVariableLabel();

}
