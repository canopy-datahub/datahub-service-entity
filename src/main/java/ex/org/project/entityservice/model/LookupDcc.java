package ex.org.project.entityservice.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "lkup_dcc")
@Data
public class LookupDcc {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", length = 150)
    private String name;

    @Column(name = "description")
    private String description;
}
