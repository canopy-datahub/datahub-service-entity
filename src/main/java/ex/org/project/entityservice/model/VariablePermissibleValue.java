package ex.org.project.entityservice.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="variable_permissible_values")
public class VariablePermissibleValue {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Column(name = "variable_id")
    private Integer variableId;

    @Column(name = "value")
    private Integer value;

    @Column(name = "label")
    private String label;

}
