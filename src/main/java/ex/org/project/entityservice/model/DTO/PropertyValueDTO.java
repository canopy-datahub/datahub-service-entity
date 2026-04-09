package ex.org.project.entityservice.model.DTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import ex.org.project.entityservice.model.PropertyValue;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PropertyValueDTO {
    private List<String> propertyValue;
    private String label;
    @JsonIgnore
    private Integer displayOrder;

    public PropertyValueDTO(List<PropertyValue> propertyValue, String label) {
        this.propertyValue = List.of(propertyValue.stream()
                .map(PropertyValue::getPropertyValue)
                .sorted()
                .collect(Collectors.joining("; "))
                                    );
        this.label = label;
        this.displayOrder = propertyValue.stream()
                .map(PropertyValue::getDisplayOrder)
                .max(Integer::compare)
                .orElse(0);
    }
}
