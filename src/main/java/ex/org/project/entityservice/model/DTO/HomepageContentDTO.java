package ex.org.project.entityservice.model.DTO;

import lombok.Data;

import java.util.List;

@Data
public class HomepageContentDTO {

    private List<HomepageContentDataDTO> newStudies;
    private List<HomepageContentDataDTO> newFiles;
    private List<HomepageContentDataDTO> updatedFiles;
}
