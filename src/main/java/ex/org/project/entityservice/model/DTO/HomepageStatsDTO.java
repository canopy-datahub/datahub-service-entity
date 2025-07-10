package ex.org.project.entityservice.model.DTO;

import lombok.Data;

import java.math.BigInteger;
import java.util.List;

@Data
public class HomepageStatsDTO {

    private Long totalFiles;
    private Long totalStudies;
    private List<DccDTO> dtos;
}