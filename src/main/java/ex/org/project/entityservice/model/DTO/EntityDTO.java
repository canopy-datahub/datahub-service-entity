
package ex.org.project.entityservice.model.DTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class EntityDTO {

    private String displayLabel;
    private String entityPropertyName;
    @JsonIgnore
    private String displaySection;
}
