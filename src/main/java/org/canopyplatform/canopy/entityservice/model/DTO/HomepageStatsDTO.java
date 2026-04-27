package org.canopyplatform.canopy.entityservice.model.DTO;

import lombok.Data;

import java.util.List;

@Data
public class HomepageStatsDTO {

    private Long totalFiles;
    private Long totalStudies;
    private List<CenterDTO> dtos;
}
