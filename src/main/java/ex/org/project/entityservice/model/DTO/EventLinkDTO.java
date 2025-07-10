package ex.org.project.entityservice.model.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventLinkDTO {

    private Integer id;
    private String linkLabel;
    private String linkUrl;
    private Integer displayOrder;
}
