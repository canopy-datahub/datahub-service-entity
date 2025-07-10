package ex.org.project.entityservice.model.DTO;

import lombok.Data;

@Data
public class StudyDocumentDTO {

    private Long id;
    private Long studyId;
    private String documentName;
    private Integer documentTypeid;
    private Long documentSize;
    private Integer s3File;
    private Integer displayOrder;
}
