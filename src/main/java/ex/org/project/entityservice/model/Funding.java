package ex.org.project.entityservice.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Entity
@NoArgsConstructor
@Table(name = "funding")
public class Funding {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "slug")
    private String slug;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;


    @Column(name = "notice_number")
    private String noticeNumber;

    @Column(name = "activity_code")
    private String activityCode;

    @Column(name = "release_date")
    private Date startDate;

    @Column(name = "expiration_date")
    private Date expirationDate;

    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "created_by")
    private Integer createdBy;

    @Column(name = "modified_at")
    private Date modifiedAt;

    @Column(name = "modified_by")
    private Integer modifiedBy;

    @Column(name = "url")
    private String linkUrl;
}
