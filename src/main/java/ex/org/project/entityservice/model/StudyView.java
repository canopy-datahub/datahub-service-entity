package ex.org.project.entityservice.model;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;

@Entity
@Table(name = "view_study")
@Data
public class StudyView {
    @Id
    @Column(name = "study_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "title")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "phs")
    private String phsNumber;

    @Column(name = "center")
    private String center;

    @Column(name = "created_at")
    private Timestamp createDate;

    @Column(name = "release_date")
    private String releaseDate;

    @Column(name = "status")
    private String status;

}
