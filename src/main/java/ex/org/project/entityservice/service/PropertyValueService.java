package ex.org.project.entityservice.service;

import ex.org.project.entityservice.exception.ResourceNotFoundException;
import ex.org.project.entityservice.model.DTO.PropertyValueDTO;
import ex.org.project.entityservice.model.PropertyValue;
import ex.org.project.entityservice.model.StudyPropertyValue;
import ex.org.project.entityservice.model.VariablePropertyValue;
import ex.org.project.entityservice.repository.StudyPropertyValueRepository;
import ex.org.project.entityservice.repository.VariablePropertyValueRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static ex.org.project.entityservice.util.Constants.PAGE_STUDY_OVERVIEW;
import static ex.org.project.entityservice.util.Constants.PAGE_VARIABLE_OVERVIEW;

@Service
@RequiredArgsConstructor
public class PropertyValueService {

    private final StudyPropertyValueRepository studyPropertyRepository;
    private final VariablePropertyValueRepository variablePropertyValueRepository;

    public Map<String, List<PropertyValueDTO>> getStudyProps(Integer studyId) {
        // Retrieve study properties from the repository
        List<StudyPropertyValue> studyProps = studyPropertyRepository.findByStudyIdAndPageOrderByDisplayOrder(
                studyId, PAGE_STUDY_OVERVIEW);
        if(studyProps.isEmpty()) {
            throw new ResourceNotFoundException("No property values found for provided study ID");
        }
        return groupPropertyValuesByDisplaySectionAndLabel(studyProps);
    }

    public Map<String, List<PropertyValueDTO>> getVariableProps(Integer variableId) {
        List<VariablePropertyValue> variableProps = variablePropertyValueRepository
                .findByVariableIdAndPageOrderByDisplayOrder(variableId, PAGE_VARIABLE_OVERVIEW);
        if(variableProps.isEmpty()) {
            throw new ResourceNotFoundException("No property values found for provided variable ID");
        }
        return groupPropertyValuesByDisplaySectionAndLabel(variableProps);
    }

    //groups a list of study property values by display section, and within these sections, consolidates mutiple
    //values that have the same label so that there is only a single object per display label per section
    private static Map<String, List<PropertyValueDTO>> groupPropertyValuesByDisplaySectionAndLabel(List<? extends PropertyValue> propertyValueList) {
        return propertyValueList.stream()
                .collect(Collectors.groupingBy(
                        PropertyValue::getDisplaySection,
                        Collectors.toList()))
                .entrySet()
                .stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> consolidateMultipleOfSameLabels(entry.getValue())));
    }

    //consolidates multiple property values into a single object if the values are for the same label
    //the new object will have each of the formerly separate property values in a single list
    private static List<PropertyValueDTO> consolidateMultipleOfSameLabels(List<? extends PropertyValue> propertyValues) {
        return propertyValues.stream()
                .collect(Collectors.groupingBy(PropertyValue::getDisplayLabel))
                .entrySet()
                .stream()
                .map(labelMap -> new PropertyValueDTO((List<PropertyValue>) labelMap.getValue(), labelMap.getKey()))
                .sorted(Comparator.comparing(PropertyValueDTO::getDisplayOrder))
                .toList();
    }

}
