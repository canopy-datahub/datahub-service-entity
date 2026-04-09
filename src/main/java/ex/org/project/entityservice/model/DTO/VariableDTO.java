package ex.org.project.entityservice.model.DTO;

import com.fasterxml.jackson.annotation.JsonInclude;
import ex.org.project.entityservice.model.StudyVariable;
import ex.org.project.entityservice.model.Variable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class VariableDTO {

    private Integer variableId;

    private String variableName;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String variableLabel;
    
    private Boolean hasOverview;

    public VariableDTO(StudyVariable variable) {
        this.variableId = variable.getVariableId();
        this.variableName = variable.getVariable();
        if(variable.getVariableLabel() != null) {
            this.variableLabel = variable.getVariableLabel();
        }
        else {
            this.variableLabel = "";
        }
        this.hasOverview = variable.getIsTier1Variable();
    }

    public VariableDTO(Variable variable) {
        this.variableId = variable.getId();
        this.variableName = variable.getName();
        this.variableLabel = variable.getLabel();
        this.hasOverview = variable.getCategory().getName().equals("Core Variable");
    }

}
