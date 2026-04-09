package ex.org.project.entityservice.model.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class StudyOverviewDTO {

    private Map<String, List<PropertyValueDTO>> props;

    private List<VariableDTO> variables;

    public StudyOverviewDTO(Map<String, List<PropertyValueDTO>> props, List<VariableDTO> variables) {
        this.props = props;
        this.variables = variables;
    }

}
