package ex.org.project.entityservice.model.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class StudyDocumentEntityDTO {

    private Integer id;
    private String documentName;
    @JsonProperty("document")
    private String lookupDataFileCategory;
    private Long documentSize;


}