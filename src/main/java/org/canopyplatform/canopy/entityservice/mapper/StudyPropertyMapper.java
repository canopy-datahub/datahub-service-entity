package org.canopyplatform.canopy.entityservice.mapper;

import org.canopyplatform.canopy.entityservice.model.DTO.StudyEntityDTO;
import org.canopyplatform.canopy.entityservice.model.StudyPropertyValue;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface StudyPropertyMapper {

    StudyEntityDTO studyPropertyValuesToStudyPropertyValueDTO(StudyPropertyValue studyPropertyValue);
    List<StudyEntityDTO> toDTOs(List<StudyPropertyValue> studyPropertyValues);

}
