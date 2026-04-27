package org.canopyplatform.canopy.entityservice.model.DTO;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
public class HomepageContentDataDTO {

    private String studyName;
    private Integer studyId;
    private String center;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer files;
    private String date;
}
