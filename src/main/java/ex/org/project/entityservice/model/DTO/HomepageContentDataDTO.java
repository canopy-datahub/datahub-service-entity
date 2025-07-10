package ex.org.project.entityservice.model.DTO;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Data
public class HomepageContentDataDTO {

    private String studyName;
    private Integer studyId;
    private String dcc;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer files;
    private String date;
}
