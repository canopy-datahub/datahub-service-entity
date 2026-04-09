package ex.org.project.entityservice.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "lkup_variable_category")
public class VariableCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(insertable = false, updatable = false, nullable = false)
    private Integer id;

    @Column(name = "name", insertable = false, updatable = false)
    private String name;

    @Column(name = "description", insertable = false, updatable = false)
    private String description;

}
