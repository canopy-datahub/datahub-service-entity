package ex.org.project.entityservice.mapper;

import ex.org.project.entityservice.model.DTO.StudyViewDTO;
import ex.org.project.entityservice.model.StudyView;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface StudyMapper {

    StudyViewDTO toStudyDTO(StudyView study);

    List<StudyViewDTO> toStudyDTOs(List<StudyView> studies);

}
