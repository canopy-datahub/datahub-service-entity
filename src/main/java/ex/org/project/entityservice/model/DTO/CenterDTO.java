package ex.org.project.entityservice.model.DTO;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CenterDTO {

    private String name;
    private Long studyCount;
    private BigDecimal totalFileSize;
    private BigDecimal dataFileCount;
    private BigDecimal documentCount;
}
