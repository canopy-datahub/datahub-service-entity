package ex.org.project.entityservice.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import ex.org.project.entityservice.model.DataFile;
import ex.org.project.entityservice.model.DTO.StudyDocumentEntityDTO;
@Mapper(componentModel = "spring")
public interface StudyDocumentMapper {
    
    @Mapping(source = "lookupDataFileCategory.name", target = "lookupDataFileCategory")
    @Mapping(source = "sourceFileName", target = "documentName")
    @Mapping(source = "fileSize", target = "documentSize")
    StudyDocumentEntityDTO studyDocumentToDto(DataFile studyDocument);

    List<StudyDocumentEntityDTO> studyDocumentsToDTOs(List<DataFile> studyDocuments);
}