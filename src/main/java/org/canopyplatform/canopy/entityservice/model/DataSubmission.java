package org.canopyplatform.canopy.entityservice.model;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;

@Entity
@Data
@Table(name="data_submission")
public class DataSubmission {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(name = "study_id")
    private Integer studyId;
    @Column(name = "submitter_user_id")
    private Integer submitterUserId;
    @Column(name = "description")
    private String description;
    @Column(name = "status_id")
    private Integer statusId;
    @Column(name = "date_submitted")
    private Date dateSubmitted;
    @Column(name = "date_approved")
    private Date dateApproved;
    @Column(name = "created_at")
    private Date createdAt;
    @Column(name = "created_by")
    private Integer createdBy;
    @Column(name = "modified_at")
    private Date modifiedAt;
    @Column(name = "modified_by")
    private Integer modifiedBy;

}
