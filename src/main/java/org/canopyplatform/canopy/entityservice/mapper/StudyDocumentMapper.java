package org.canopyplatform.canopy.entityservice.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import org.canopyplatform.canopy.entityservice.model.DataFile;
import org.canopyplatform.canopy.entityservice.model.DTO.StudyDocumentEntityDTO;
@Mapper(componentModel = "spring")
public interface StudyDocumentMapper {

    @Mapping(source = "lookupDataFileCategory.name", target = "lookupDataFileCategory")
    @Mapping(source = "sourceFileName", target = "documentName")
    @Mapping(source = "fileSize", target = "documentSize")
    StudyDocumentEntityDTO studyDocumentToDto(DataFile studyDocument);

    List<StudyDocumentEntityDTO> studyDocumentsToDTOs(List<DataFile> studyDocuments);
}
