package ex.org.project.entityservice.mapper;

import ex.org.project.entityservice.model.DTO.StudyEntityDTO;
import ex.org.project.entityservice.model.StudyPropertyValue;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface StudyPropertyMapper {

    StudyEntityDTO studyPropertyValuesToStudyPropertyValueDTO(StudyPropertyValue studyPropertyValue);
    List<StudyEntityDTO> toDTOs(List<StudyPropertyValue> studyPropertyValues);

}
