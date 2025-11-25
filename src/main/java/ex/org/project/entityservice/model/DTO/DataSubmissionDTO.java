package ex.org.project.entityservice.model.DTO;

import lombok.Data;

import java.sql.Date;

@Data
public class DataSubmissionDTO {

    private Integer id;
    private Integer studyId;
    private Integer submitterUserId;
    private String description;
    private Integer statusId;
    private Date dateSubmitted;
    private Date dateApproved;
    private Date createdAt;
    private Integer createdBy;
    private Date modifiedAt;
    private Integer modifiedBy;

}
