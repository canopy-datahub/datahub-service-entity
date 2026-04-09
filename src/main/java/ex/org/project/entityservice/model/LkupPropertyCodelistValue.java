package ex.org.project.entityservice.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "lkup_property_codelist_value")
public class LkupPropertyCodelistValue {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "property_codelist_id")
    private LkupPropertyCodelist propertyCodelist;

    private String value;

}
