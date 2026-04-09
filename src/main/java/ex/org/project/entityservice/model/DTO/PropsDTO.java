package ex.org.project.entityservice.model.DTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class PropsDTO  {

    String displayLabel;
    String EntityPropertyName;
    @JsonIgnore
    String DisplaySection;

    Boolean Sortable;

}