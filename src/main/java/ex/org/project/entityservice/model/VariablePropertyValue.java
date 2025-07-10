package ex.org.project.entityservice.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "view_variable_overview_display")
@Data
public class VariablePropertyValue extends PropertyValue {

    @Id
    @Column(name = "variable_property_value_id")
    private Long id;

    @Column(name = "variable_id")
    private Integer variableId;

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
