package org.canopyplatform.canopy.entityservice.model;


import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name = "study_document")
@Data
public class StudyDocument {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Column(name = "study_id")
    private Long studyId;

    @Column(name = "document_name")
    private String documentName;

    @Column(name = "document_type_id")
    private Integer documentTypeId;

    @Column(name = "document_size")
    private Long documentSize;

    @Column(name = "s3_file_id")
    private Integer s3File;

    @Column(name = "display_order")
    private Integer displayOrder;

    @ManyToOne
    @Fetch(FetchMode.JOIN)
    @JoinColumn(name = "document_type_id", referencedColumnName = "id", insertable = false, updatable = false)
    private LookupDataFileCategory lookupDataFileCategory;

}


