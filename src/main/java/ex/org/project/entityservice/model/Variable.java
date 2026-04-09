package ex.org.project.entityservice.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "variables")
public class Variable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", insertable = false, unique = true, nullable = false)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private VariableCategory category;

    @Column(name = "name")
    private String name;
    
    @Column(name = "label")
    private String label;

}
