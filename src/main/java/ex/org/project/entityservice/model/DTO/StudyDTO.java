package ex.org.project.entityservice.model.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudyDTO {

    private Integer id;
    private String uuid;
    private String privateKeyUrl;
    private String publicKeyUrl;
    private String fileName;
    private String fileUrl;
    private String dccAdminUuid;
    private Date createdAt;
    private Integer createdBy;
    private Date modifiedAt;
    private Integer modifiedBy;
    private Integer dccId;

}
