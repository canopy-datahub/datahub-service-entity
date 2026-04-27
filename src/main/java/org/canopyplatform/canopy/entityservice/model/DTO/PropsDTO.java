package org.canopyplatform.canopy.entityservice.model.DTO;

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
