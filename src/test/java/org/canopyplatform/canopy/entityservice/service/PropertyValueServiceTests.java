package org.canopyplatform.canopy.entityservice.service;

import org.canopyplatform.canopy.entityservice.exception.ResourceNotFoundException;
import org.canopyplatform.canopy.entityservice.model.DTO.PropertyValueDTO;
import org.canopyplatform.canopy.entityservice.model.LkupStatus;
import org.canopyplatform.canopy.entityservice.model.StudyPropertyValue;
import org.canopyplatform.canopy.entityservice.model.StudyView;
import org.canopyplatform.canopy.entityservice.repository.StudyPropertyValueRepository;
import org.canopyplatform.canopy.entityservice.repository.VariablePropertyValueRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PropertyValueServiceTests {

    @Mock
    private VariablePropertyValueRepository variablePropertyValueRepository;
    @Mock
    private StudyPropertyValueRepository studyPropertyValueRepository;

    @InjectMocks
    private PropertyValueService propertyValueService;

    private StudyPropertyValue createStudyEntityDTO(String displaySection, String displayLabel, String propertyValue) {
        StudyPropertyValue studyDTO = new StudyPropertyValue();
        studyDTO.setDisplaySection(displaySection);
        studyDTO.setDisplayLabel(displayLabel);
        studyDTO.setPropertyValue(propertyValue);
        studyDTO.setDisplayOrder(10);
        return studyDTO;
    }

    @Test
    void testGetStudyProps() {
        // Set up mock data
        Integer studyId = 27;
        String page = "study_overview";
        StudyPropertyValue studyDTO1 = createStudyEntityDTO("section1", "label1", "value1");
        StudyPropertyValue studyDTO2 = createStudyEntityDTO("section1", "label2", "value2");
        List<StudyPropertyValue> studyDTOS = Arrays.asList(studyDTO1, studyDTO2);


        // Set up the mock behavior
        when(studyPropertyValueRepository.findByStudyIdAndPageOrderByDisplayOrder(27, "study_overview"))
                .thenReturn(studyDTOS);
        Map<String, List<PropertyValueDTO>> studyProps = propertyValueService.getStudyProps(studyId);

        // Verify the result
        assertEquals(1, studyProps.size());
        List<PropertyValueDTO> section1Props = studyProps.get("section1");
        assertEquals(2, section1Props.size());

        PropertyValueDTO prop1 = section1Props.get(0);
        assertEquals("label1", prop1.getLabel());
        assertEquals(Arrays.asList("value1"), prop1.getPropertyValue());

        PropertyValueDTO prop2 = section1Props.get(1);
        assertEquals("label2", prop2.getLabel());
        assertEquals(Arrays.asList("value2"), prop2.getPropertyValue());

        // Verify the method calls
        verify(studyPropertyValueRepository, times(1))
                .findByStudyIdAndPageOrderByDisplayOrder(studyId, page);

    }

    @Test
    void testGetStudyProps_EmptyList() {
        Integer studyId = 27;
        String page = "study_overview";
        StudyView study = new StudyView();
        study.setId(studyId);
        LkupStatus approvedStatus = new LkupStatus(27, "Approved", "study");
        study.setStatus(approvedStatus.getName());

        // Set up the mock behavior
        when(studyPropertyValueRepository.findByStudyIdAndPageOrderByDisplayOrder(27, "study_overview"))
                .thenReturn(Collections.emptyList());

        assertThrows(ResourceNotFoundException.class, () -> propertyValueService.getStudyProps(studyId));

        // Verify the method calls
        verify(studyPropertyValueRepository, times(1))
                .findByStudyIdAndPageOrderByDisplayOrder(studyId, page);
    }

    @Test
    void testMergingProperties(){
        Integer studyId = 27;
        String page = "study_overview";
        StudyView study = new StudyView();
        study.setId(studyId);
        LkupStatus approvedStatus = new LkupStatus(27,"Approved","study");
        study.setStatus(approvedStatus.getName());
        StudyPropertyValue studyDTO1 = createStudyEntityDTO("section1", "label1", "value1");
        StudyPropertyValue studyDTO2 = createStudyEntityDTO("section1", "label1", "value2");
        List<StudyPropertyValue> studyDTOS = Arrays.asList(studyDTO1, studyDTO2);

        when(studyPropertyValueRepository.findByStudyIdAndPageOrderByDisplayOrder(27, "study_overview"))
                .thenReturn(studyDTOS);
        Map<String, List<PropertyValueDTO>> studyProps = propertyValueService.getStudyProps(studyId);

        assertEquals(1, studyProps.size());
        List<PropertyValueDTO> section1Props = studyProps.get("section1");
        assertEquals(1, section1Props.size());

        PropertyValueDTO prop1 = section1Props.get(0);
        assertEquals("label1", prop1.getLabel());
        assertEquals(Arrays.asList("value1; value2"), prop1.getPropertyValue());

        // Verify the method calls
        verify(studyPropertyValueRepository, times(1))
                .findByStudyIdAndPageOrderByDisplayOrder(studyId, page);
    }

}
