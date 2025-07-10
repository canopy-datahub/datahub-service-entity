package ex.org.project.entityservice.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import ex.org.project.entityservice.model.StudyView;
import ex.org.project.entityservice.model.DTO.StudyViewDTO;

@Mapper(componentModel = "spring")
public interface StudyMapper {

    StudyViewDTO toStudyDTO(StudyView study);
    
    List<StudyViewDTO> toStudyDTOs(List<StudyView> studies);

}
