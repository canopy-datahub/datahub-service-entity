
package ex.org.project.entityservice.model;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "view_study_property_value_display")
@Data
public class StudyPropertyValue extends PropertyValue {

    @Id
    @Column(name = "study_property_value_id")
    private Integer id;

    @Column(name = "study_id")
    private Long studyId;

    @Column(name = "entity_property_id")
    private Integer entityPropertyId;

    @Column(name = "property_value")
    private String propertyValue;

    @Column(name = "entity_property_name")
    private String entityPropertyName;

    @Column(name = "property_type_id")
    private Integer propertyTypeId;

    @Column(name = "entity_property_type")
    private String entityPropertyType;

    @Column(name = "entity_property_display_setting_id")
    private Integer entityPropertyDisplaySettingId;

    @Column(name = "page")
    private String page;

    @Column(name = "display_section")
    private String displaySection;

    @Column(name = "display_label")
    private String displayLabel;

    @Column(name = "display_order")
    private Integer displayOrder;

    @Column(name = "is_facet")
    private Boolean isFacet;

    @Column(name = "facet_order")
    private Integer facetOrder;

    @Column(name = "is_sortable")
    private Boolean isSortable;
}

