package ex.org.project.entityservice.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name="lkup_data_file_category")
public class LookupDataFileCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;
    private String description;
    private String categoryGroup;
    private Integer displayOrder;

}
