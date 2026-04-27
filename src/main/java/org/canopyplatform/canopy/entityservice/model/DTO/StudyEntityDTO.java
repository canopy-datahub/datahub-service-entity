package org.canopyplatform.canopy.entityservice.model.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudyEntityDTO {

    private String displayLabel;
    private String displaySection;

    private Long studyId;
    private String propertyValue;
}
