package org.canopyplatform.canopy.entityservice.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import org.canopyplatform.canopy.entityservice.model.StudyView;
import org.canopyplatform.canopy.entityservice.model.DTO.StudyViewDTO;

@Mapper(componentModel = "spring")
public interface StudyMapper {

    StudyViewDTO toStudyDTO(StudyView study);

    List<StudyViewDTO> toStudyDTOs(List<StudyView> studies);

}
