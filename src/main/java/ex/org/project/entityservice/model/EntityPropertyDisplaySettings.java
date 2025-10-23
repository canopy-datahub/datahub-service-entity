package ex.org.project.entityservice.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

@Entity
@Table(name = "entity_property_display_setting")
@Data
@NoArgsConstructor
public class EntityPropertyDisplaySettings {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private Integer id;

    @Column(name = "display_label")
    @Value("#{displayLabel}")
     String displayLabel;

    @Column(name = "facet_order")
    private Integer facetOrder;

    @Column(name = "is_facet")
    private Boolean isFacet;

    @Column(name = "display_section")
    private String displaySection;

    @Column(name = "page")
    private String page;

    @Column(name="display_order")
    private String displayOrder;

    @ManyToOne
    @JoinColumn(name = "entity_property_id")
    private EntityProperty entityProperty;


    @Column(name = "is_sortable")
    private Boolean sortable;
}
