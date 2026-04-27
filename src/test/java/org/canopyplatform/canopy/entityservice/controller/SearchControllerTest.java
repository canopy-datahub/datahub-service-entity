package org.canopyplatform.canopy.entityservice.controller;

import org.canopyplatform.canopy.entityservice.model.DTO.PropsDTO;
import org.canopyplatform.canopy.entityservice.service.EntityService;
import org.canopyplatform.canopy.entityservice.model.DTO.EntityDTO;
import org.canopyplatform.canopy.entityservice.service.VariableService;
import org.springframework.http.ResponseEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SearchControllerTest {

    @Mock
    private EntityService entityService;

    @Mock
    private VariableService variableService;

    @InjectMocks
    private SearchController searchController;

    @BeforeEach
    void setup() {
        entityService = mock(EntityService.class);
        searchController = new SearchController(entityService, variableService);
    }

    @Test
    void testGetFacets() {
        // Mock data
        List<EntityDTO> mockStudyFacets = new ArrayList<>();
        EntityDTO studyEntity = new EntityDTO();
        studyEntity.setEntityPropertyName("study_name");
        studyEntity.setEntityPropertyName("study_id");
        mockStudyFacets.add(studyEntity);

        List<EntityDTO> mockVariableFacets = new ArrayList<>();
        EntityDTO variableEntity = new EntityDTO();
        variableEntity.setEntityPropertyName("id");
        variableEntity.setEntityPropertyName("label");
        mockVariableFacets.add(variableEntity);


        // Mock the behavior of the entityService.getDisplaySettingsByStudy() method to return the mockFacets list.
        when(entityService.getDisplaySettingsByEntityType("study")).thenReturn(mockStudyFacets);
        when(entityService.getDisplaySettingsByEntityType("variable")).thenReturn(mockVariableFacets);

        ResponseEntity<List<EntityDTO>> studyResult = searchController.getFacets("study");
        ResponseEntity<List<EntityDTO>> variableResult = searchController.getFacets("variable");

        // Assert that the result is equal to the mockFacets list
        assertEquals(ResponseEntity.ok(mockStudyFacets), studyResult);
        assertEquals(ResponseEntity.ok(mockVariableFacets), variableResult);
    }

    @Test
    void testGetFacetsReturnsNonNullList() {
        // Call the getFacets() method of the searchController and store the result.
        ResponseEntity<List<EntityDTO>> result = searchController.getFacets("study");

        // Assert that the result is not null.
        assertNotNull(result);
    }

    @Test
    void testGetProps() {
        // Mock data
        Map<String, List<PropsDTO>> displaySettingsMap = new HashMap<>();
        List<PropsDTO> propsList = new ArrayList<>();
        PropsDTO getPropsDTO = mock(PropsDTO.class); // Create mock object
        propsList.add(getPropsDTO);
        displaySettingsMap.put("DisplaySection", propsList);
        // Mock the behavior of the entityService.getProps() method to return the displaySettingsMap.
        when(entityService.getProps()).thenReturn(displaySettingsMap);
        // Call the getProps() method of the searchController and store the result.
        ResponseEntity<Map<String, List<PropsDTO>>> response = searchController.getProps();

        assertNotNull(response);
        // Assert that the response contains the "DisplaySection" key.
        assertTrue(response.getBody().containsKey("DisplaySection"));

    }

    @Test
        //Verifies the behavior when the displaySettingsMap is empty
    void testGetProps_WhenDisplaySettingsMapIsEmpty_ReturnsEmptyMap() {
        Map<String, List<PropsDTO>> emptyMap = new HashMap<>();
        //when(entityService.getProps()).thenReturn(emptyMap);
        ResponseEntity<Map<String, List<PropsDTO>>> response = searchController.getProps();

        // Assert
        assertNotNull(response);
        assertTrue(response.getBody().isEmpty());
    }

    @Test
        //when the DisplaySection in the displaySettingsMap has an empty props list.
    void testGetProps_WhenDisplaySectionHasEmptyPropsList_ReturnsMapWithEmptyPropsList() {
        Map<String, List<PropsDTO>> displaySettingsMap = new HashMap<>();
        List<PropsDTO> emptyPropsList = new ArrayList<>();
        displaySettingsMap.put("DisplaySection", emptyPropsList);
        when(entityService.getProps()).thenReturn(displaySettingsMap);

        ResponseEntity<Map<String, List<PropsDTO>>> response = searchController.getProps();

        // Assert
        assertNotNull(response);
        assertTrue(response.getBody().containsKey("DisplaySection"));
        assertTrue(response.getBody().get("DisplaySection").isEmpty());
    }

}


