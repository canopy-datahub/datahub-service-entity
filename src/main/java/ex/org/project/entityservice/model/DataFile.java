package ex.org.project.entityservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.sql.Date;

@Entity
@Data
@Table(name="data_file")
@NoArgsConstructor
@AllArgsConstructor
public class DataFile {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;
    @ManyToOne
    @Fetch(FetchMode.JOIN)
    @JoinColumn(name = "submission_id", referencedColumnName = "id", insertable = false, updatable = false)
    private DataSubmission dataSubmission;
    @Column(name = "source_file_name")
    private String sourceFileName;
    @Column(name = "normalized_file_name")
    private String normalizedFileName;
    @Column(name="version_no")
    private String versionNumber;
    @Column(name = "is_current_version")
    private Boolean isCurrentVersion;
    @Column(name = "original_data_file_id")
    private Integer originalDataFileId;
    @ManyToOne
    @Fetch(FetchMode.JOIN)
    @JoinColumn(name = "file_category_id", referencedColumnName = "id", insertable = false, updatable = false)
    private LookupDataFileCategory lookupDataFileCategory;
    @Column(name = "file_size")
    private Long fileSize;
    @Column(name = "pii_phi")
    private Boolean piiPhi;
    @ManyToOne
    @Fetch(FetchMode.JOIN)
    @JoinColumn(name = "status_id", referencedColumnName = "id", insertable = false, updatable = false)
    private LkupStatus status;
    @Column(name = "s3_file_id")
    private Integer s3FileId;
    @OneToOne
    @JoinColumn(name = "dictionary_file_id")
    private DataFile dictionaryFile;
    @OneToOne
    @JoinColumn(name = "metadata_file_id")
    private DataFile metadataFile;
    @Column(name = "comments")
    private String comments;
    @Column(name = "approval_date")
    private Date approvalDate;
    @Column(name = "created_at")
    private Date createdAt;
    @Column(name = "created_by")
    private Integer createdBy;
    @Column(name = "modified_at")
    private Date modifiedAt;
    @Column(name = "modified_by")
    private Integer modifiedBy;
    @Column(name = "file_headers")
    private String fileHeaders;
    @Column(name="variable_count")
    private Integer variablesCount;
    @Column(name="sample_size")
    private Integer sampleSize;

    @Transient
    private Boolean sasAvailable;

}
