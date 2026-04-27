package org.canopyplatform.canopy.entityservice.service;

import org.canopyplatform.canopy.entityservice.exception.ResourceNotFoundException;
import org.canopyplatform.canopy.entityservice.model.DTO.PropertyValueDTO;
import org.canopyplatform.canopy.entityservice.model.PropertyValue;
import org.canopyplatform.canopy.entityservice.model.StudyPropertyValue;
import org.canopyplatform.canopy.entityservice.model.VariablePropertyValue;
import org.canopyplatform.canopy.entityservice.repository.StudyPropertyValueRepository;
import org.canopyplatform.canopy.entityservice.repository.VariablePropertyValueRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.canopyplatform.canopy.entityservice.util.Constants.PAGE_STUDY_OVERVIEW;
import static org.canopyplatform.canopy.entityservice.util.Constants.PAGE_VARIABLE_OVERVIEW;

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
