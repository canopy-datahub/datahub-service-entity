package org.canopyplatform.canopy.entityservice.model.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewsLinkDTO {

    private Long id;
    private String linkLabel;
    private String linkUrl;
    private Integer displayOrder;
}
