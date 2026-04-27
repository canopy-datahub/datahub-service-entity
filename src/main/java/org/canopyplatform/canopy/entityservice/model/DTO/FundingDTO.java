package org.canopyplatform.canopy.entityservice.model.DTO;

import lombok.Data;

import java.util.Date;

@Data
public class FundingDTO {
    private Long id;
    private String slug;
    private String title;
    private String description;
    private String noticeNumber;
    private String activityCode;
    private Date startDate;
    private Date expirationDate;
    private Date createdAt;
    private String linkUrl;
}
