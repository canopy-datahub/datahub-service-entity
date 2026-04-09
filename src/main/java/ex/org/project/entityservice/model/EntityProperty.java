package ex.org.project.entityservice.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name = "entity_property")
@Data
public class EntityProperty {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private Integer id;


    @Column(name="name")
    private String name;

    @ManyToOne
    @Fetch(FetchMode.JOIN)
    @JoinColumn(name = "entity_type_id")
    private LookupEntityType lookupEntityType;

    @ManyToOne(fetch = FetchType.LAZY)
    private LkupPropertyType propertyType;

    private Integer codeListId;

    private Integer propertySourceId;

}
