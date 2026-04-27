package org.canopyplatform.canopy.entityservice.controller;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.canopyplatform.canopy.entityservice.auth.AccessRole;
import org.canopyplatform.canopy.entityservice.auth.UserAuthorizationException;
import org.canopyplatform.canopy.entityservice.auth.core.KeycloakAuthenticationService;
import org.canopyplatform.canopy.entityservice.exception.custom.StudyNotFoundException;
import org.canopyplatform.canopy.entityservice.model.DTO.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.jwt.Jwt;

import org.canopyplatform.canopy.entityservice.service.EntityService;

@ExtendWith(MockitoExtension.class)
class StudyControllerTest {

    @Mock
    private EntityService entityService;


    private KeycloakAuthenticationService authenticationService;

  @InjectMocks
    private StudyController studyController;

    @Test
    void testGetStudy() {
        Integer studyId = 1;
        Jwt jwt = mock(Jwt.class);
        StudyOverviewDTO studyOverviewDTO = new StudyOverviewDTO();

        when(authenticationService.checkAuth(eq(jwt), any())).thenReturn(any());
        when(entityService.getStudyOverview(1)).thenReturn(studyOverviewDTO);
        ResponseEntity<StudyOverviewDTO> result = studyController.getStudy(jwt, studyId);

        assertEquals(studyOverviewDTO, result.getBody());
    }

    @Test
    void testGetStudyUnauthorized() {
        Integer studyId = 1;
        Jwt jwt = mock(Jwt.class);

        when(entityService.isApprovedStudy(studyId)).thenReturn(false);
        when(authenticationService.checkAuth(eq(jwt), eq(List.of(AccessRole.DATA_CURATOR)))).thenThrow(
            new UserAuthorizationException("User does not have the necessary role for access"));
        // Assert that the UserAuthorizationException is thrown when user is unauthorized to access study
        assertThrows(UserAuthorizationException.class, () -> studyController.getStudy(jwt, studyId));

    }

    @Test
    void testGetStudyWithNullStudyId() {
        Integer studyId = null;
        Jwt jwt = mock(Jwt.class);

        when(authenticationService.checkAuth(eq(jwt), any())).thenReturn(any());
        when(entityService.getStudyOverview(null)).thenThrow(
            new StudyNotFoundException("No study found with ID: " + studyId));

        assertThrows(StudyNotFoundException.class, () -> studyController.getStudy(jwt, studyId));
    }

    @Test
    void testGetStudyWithValidStudyIdAndValidStudyProperty() {
        Integer studyId = 1;
        Jwt jwt = mock(Jwt.class);
        StudyOverviewDTO expected = new StudyOverviewDTO();
        Map<String, List<PropertyValueDTO>> props = new HashMap<>();
        PropertyValueDTO studyProp1 = new PropertyValueDTO(List.of("phs001234"), "dbGaP Study Accession", 1);
        props.put("Representative", List.of(studyProp1));
        expected.setProps(props);

        // Call the getStudy() method
        when(authenticationService.checkAuth(eq(jwt), any())).thenReturn(any());
        when(entityService.getStudyOverview(studyId)).thenReturn(expected);
        ResponseEntity<StudyOverviewDTO> result = studyController.getStudy(jwt, studyId);
        assertEquals(expected, result.getBody());

    }

    @Test
    void testGetDocuments() {
        // Mock data
        Integer studyId = 1;
        Jwt jwt = mock(Jwt.class);
        List<StudyDocumentEntityDTO> mockDocuments = new ArrayList<>();
        when(authenticationService.checkAuth(eq(jwt), any())).thenReturn(any());
        when(entityService.getStudyDocuments(studyId)).thenReturn(mockDocuments);

        ResponseEntity<List<StudyDocumentEntityDTO>> result = studyController.getDocuments(jwt, studyId);
        assertEquals(mockDocuments, result.getBody());
    }

    @Test
    void testGetDocumentsShouldReturnEmptyListWhenNoDocumentsFound() {
        // Mock data
        Integer studyId = 1;
        Jwt jwt = mock(Jwt.class);
        List<StudyDocumentEntityDTO> emptyList = new ArrayList<>();
        when(authenticationService.checkAuth(eq(jwt), any())).thenReturn(any());
        when(entityService.getStudyDocuments(studyId)).thenReturn(new ArrayList<>());
        ResponseEntity<List<StudyDocumentEntityDTO>> result = studyController.getDocuments(jwt, studyId);

        // Assert that the result is an empty list
        assertEquals(emptyList, result.getBody());
    }

    @Test
    void testGetDocumentsShouldReturnEmptyListWhenStudyIdIsNull() {
        Boolean accessGranted = true;
        Jwt jwt = mock(Jwt.class);
        List<StudyDocumentEntityDTO> emptyList = new ArrayList<>();
        when(authenticationService.checkAuth(eq(jwt), any())).thenReturn(any());
        ResponseEntity<List<StudyDocumentEntityDTO>> result = studyController.getDocuments(jwt, null);

        // Assert that the result is an empty list
        assertEquals(emptyList, result.getBody());
    }

    @Test
    void testGetDatasets() {
        // Mock data
        DataFileDTO testDTO = new DataFileDTO(1, "sourceFile", "normalizedFile", 3, 4, "csv", 12345L, "Document", 12, 15,
                                              "record_id;f_name;l_name;race;ethnicity;dob;age;sex;education;zip;employment",
                                              0, "metadatafileName", 0, 1
        );
        DataFileDTO testDTO2 = new DataFileDTO(2, "sourceFile", "normalizedFile", 1, 2, "csv", 12345L, "Document", 12, 15,
                                               "record_id;f_name;l_name;race;ethnicity;dob;age;sex;education;zip;employment",
                                               0, "metadatafileName", 0, 1
        );
        Integer studyId = 1;
        Jwt jwt = mock(Jwt.class);
        Integer userId = 1;
        List<DataFileDTO> mockDatasets = new ArrayList<>();
        DatasetDTO datasetDTO = new DatasetDTO();
        mockDatasets.add(testDTO);
        mockDatasets.add(testDTO2);
        datasetDTO.setDataFileDTOS(mockDatasets);
        datasetDTO.setUserHasStudyAccess(true);
        when(entityService.isApprovedStudy(studyId)).thenReturn(true);
        when(entityService.getDatasets(userId, studyId)).thenReturn(datasetDTO);
        when(authenticationService.checkAuth(eq(jwt))).thenReturn(userId);
        ResponseEntity<DatasetDTO> response = studyController.getDatasets(jwt, studyId);

        assertEquals(datasetDTO, response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void testGetDatasetsShouldReturnEmptyListWhenNoDatasetsFound() {

        // Mock data
        Integer studyId = 1;
        Jwt jwt = mock(Jwt.class);
        Integer userId = 1;
        List<DataFileDTO> mockDatasets = new ArrayList<>();
        DatasetDTO datasetDTO = new DatasetDTO();
        datasetDTO.setDataFileDTOS(mockDatasets);
        when(authenticationService.checkAuth(eq(jwt))).thenReturn(userId);
        when(entityService.isApprovedStudy(studyId)).thenReturn(true);
        when(entityService.getDatasets(eq(userId), eq(studyId))).thenReturn(datasetDTO);

        ResponseEntity<DatasetDTO> response = studyController.getDatasets(jwt, studyId);
        assertEquals(datasetDTO.toString(), response.getBody().toString());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

}
