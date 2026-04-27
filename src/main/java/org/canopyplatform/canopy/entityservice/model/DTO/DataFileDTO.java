package org.canopyplatform.canopy.entityservice.model.DTO;

public record DataFileDTO(
    Integer dataFileId,
    String sourceFileName,
    String normalizedFileName,
    Integer dictionaryFileId,
    Integer metadataFileId,
    String fileFormat,
    Long fileSize,
    String category,
    Integer totalVariables,
    Integer sampleSize,
    String dataVariables,
    Integer metadataFileSize,
    String metadataFileName,
    Integer dictionaryFileSize,
    Integer versionNumber
){}
