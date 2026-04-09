package ex.org.project.entityservice.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Table(name = "study")
@Data
public class Study {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "uuid")
    private String uuid;

    @Column(name = "private_key_url")
    private String privateKeyUrl;

    @Column(name = "public_key_url")
    private String publicKeyUrl;

    @Column(name = "file_name")
    private String fileName;

    @Column(name = "file_url")
    private String fileUrl;

    @Column(name = "center_admin_uuid")
    private String centerAdminUuid;

    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "created_by")
    private Integer createdBy;

    @Column(name = "modified_at")
    private Date modifiedAt;

    @Column(name = "modified_by")
    private Integer modifiedBy;

    @ManyToOne
    @JoinColumn(name = "center_id")
    private LookupCenter centerId;

}
