package ex.org.project.entityservice.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="lkup_core_variable_permissible_value")
public class VariablePermissibleValue {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Column(name = "variable_name")
    private String variableName;

    @Column(name = "value")
    private Integer value;

    @Column(name = "label")
    private String label;

}
