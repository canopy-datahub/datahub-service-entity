package ex.org.project.entityservice.model.DTO;

import lombok.Data;

import java.util.List;

@Data
public class DatasetDTO {
    private boolean userHasStudyAccess;
    List<DataFileDTO> dataFileDTOS;

}
