package org.canopyplatform.canopy.entityservice.mapper;

import java.util.List;

import org.apache.commons.io.FilenameUtils;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import org.canopyplatform.canopy.entityservice.model.DataFile;
import org.canopyplatform.canopy.entityservice.model.DTO.DataFileDTO;

@Mapper(componentModel="spring")
public interface DataFileMapper {

    @Mapping(source = "lookupDataFileCategory.name", target="category")
    @Mapping(source = ".", target = "fileFormat", qualifiedByName="extractFileExtension")
    @Mapping(source = "dataFile.fileHeaders", target = "dataVariables")
    @Mapping(source = "dataFile.variablesCount", target="totalVariables")
    @Mapping(source = "dataFile.sampleSize", target = "sampleSize")
    @Mapping(source = "dataFile.id", target = "dataFileId")
    @Mapping(source = "dataFile.metadataFile.id", target = "metadataFileId")
    @Mapping(source = "dataFile.metadataFile.fileSize", target = "metadataFileSize")
    @Mapping(source = "dataFile.metadataFile.sourceFileName", target = "metadataFileName")
    @Mapping(source = "dataFile.dictionaryFile.id", target = "dictionaryFileId")
    @Mapping(source = "dataFile.dictionaryFile.fileSize", target = "dictionaryFileSize")
    DataFileDTO dataFileToDto(DataFile dataFile);

    List<DataFileDTO> dataFileListToDtoList(List<DataFile> dataFiles);

    @Named("extractFileExtension")
    static String sourceFileNameToFileExtension(DataFile dataFile){
        String sourceFileName = dataFile.getSourceFileName();
        String fileFormat = FilenameUtils.getExtension(sourceFileName);
        if(dataFile.getSasAvailable()) {
            fileFormat = String.format("%s, SAS", fileFormat);
        }
        return fileFormat;
    }

}
