package ex.org.project.entityservice.service;

import ex.org.project.datahub.auth.core.KeycloakAuthenticationService;
import ex.org.project.entityservice.exception.ResourceNotFoundException;
import ex.org.project.datahub.auth.core.FileAuthorizationService;
import ex.org.project.entityservice.mapper.*;
import ex.org.project.entityservice.model.DTO.PropsDTO;
import ex.org.project.entityservice.model.DTO.StudyDocumentEntityDTO;
import ex.org.project.entityservice.model.DataFile;
import ex.org.project.entityservice.model.EntityPropertyDisplaySettings;
import ex.org.project.entityservice.model.LkupStatus;
import ex.org.project.entityservice.model.StudyView;
import ex.org.project.entityservice.repository.*;
import ex.org.project.entityservice.util.Constants;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
class EntityServiceTest {

    @Mock
    private EntityPropertyDisplaySettingsRepository entityPropertyDisplaySettingsRepository;

    @Mock
    private LkupPropertyCodelistValueRepository codelistValueRepository;

    @Mock
    private LkupPropertySourceRepository propertySourceRepository;

    @Mock
    private StudyDocumentRepository studyDocumentRepository;

    @Mock
    private DataFileRepository dataFileRepository;

    @Mock
    private StudyRepository studyRepository;

    @Mock
    private LkupStatusRepository statusRepository;

    @Mock
    private DataFileMapper dataFileMapper;

    @Mock
    private EntityPropertyRepository entityPropertyRepository;

    @Mock
    private StudyDocumentMapper documentMapper;

    @Mock
    private EntityPropertyMapper entityPropertyMapper;

    @Mock
    private EntityDTOMapper entityDTOMapper;

    @Mock
    private PropertyValueService propertyValueService;

    @Mock
    private StudyEntityRepository studyEntityRepository;

    @Mock
    private FileAuthorizationService fileAuthorizationService;

    @InjectMocks
    private EntityService entityService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        StudyPropertyMapper studyMapper = Mappers.getMapper(StudyPropertyMapper.class);
        entityService = new EntityService(entityPropertyDisplaySettingsRepository, codelistValueRepository,
                                          propertySourceRepository, entityPropertyRepository, dataFileRepository,
                                          studyRepository, statusRepository, dataFileMapper, entityDTOMapper,
                                          documentMapper, entityPropertyMapper, fileAuthorizationService, propertyValueService,
                                          studyEntityRepository
        );
    }

    @Test
    void testGetProps_EmptyEntityDTOList() {
        // Set up test data
        List<EntityPropertyDisplaySettings> entityDTOList = Collections.emptyList();
        when(entityPropertyDisplaySettingsRepository.findByPage_OrderByDisplayOrder("study_search")).thenReturn(
            entityDTOList);
        // Call the service method
        Map<String, List<PropsDTO>> props = entityService.getProps();

        // Assert
        Assert.assertNotNull(props);
        Assert.assertTrue(props.isEmpty());

        // Verify the interactions with the repository
        verify(entityPropertyDisplaySettingsRepository, times(1)).findByPage_OrderByDisplayOrder("study_search");
        verifyNoMoreInteractions(entityPropertyDisplaySettingsRepository);
    }

    @Test
    void testGetProps() {
        // Create a list of EntityPropertyDisplaySettings to be returned by the mock EntityPropertyDisplaySettingsRepository
        List<EntityPropertyDisplaySettings> displaySettingsList = new ArrayList<>();
        displaySettingsList.add(new EntityPropertyDisplaySettings());
        displaySettingsList.add(new EntityPropertyDisplaySettings());

        List<PropsDTO> propsDTOs = new ArrayList<>();
        propsDTOs.add(new PropsDTO());
        propsDTOs.add(new PropsDTO());

        // Mock the behavior of the EntityPropertyDisplaySettingsRepository's findByPage_OrderByDisplayOrder method to return the list of EntityPropertyDisplaySettings
        when(entityPropertyDisplaySettingsRepository.findByPage_OrderByDisplayOrder(
            Constants.PAGE_STUDY_SEARCH)).thenReturn(displaySettingsList);

        // Mock the behavior of the EntityMapper's proptoDTOs method to return the list of PropsDTO
        when(entityDTOMapper.proptoDTOs(displaySettingsList)).thenReturn(propsDTOs);
        Map<String, List<PropsDTO>> propsMap = entityService.getProps();

        verify(entityPropertyDisplaySettingsRepository, times(1)).findByPage_OrderByDisplayOrder(
            Constants.PAGE_STUDY_SEARCH);
        verify(entityDTOMapper, times(1)).proptoDTOs(displaySettingsList);
    }

    @Test
    void testGetStudyDocuments() {
        // Create sample data
        Integer studyId = 1;
        StudyView study = new StudyView();
        study.setId(studyId);
        LkupStatus approvedStatus = new LkupStatus(27, "Approved", "study");
        study.setStatus(approvedStatus.getName());
        List<DataFile> StudyDocuments = new ArrayList<>();
        StudyDocuments.add(new DataFile());
        StudyDocuments.add(new DataFile());

        // Mock the repository method
        when(
            dataFileRepository.findByDataSubmission_StudyIdAndIsCurrentVersionTrueAndLookupDataFileCategoryCategoryGroup(
                studyId, "document")).thenReturn(StudyDocuments);
        when(studyRepository.findById(studyId)).thenReturn(Optional.of(study));
        when(statusRepository.findByUsageAndName("study", "Approved")).thenReturn(approvedStatus);

        // Mock the mapper method
        List<StudyDocumentEntityDTO> expectedDTOs = new ArrayList<>();
        expectedDTOs.add(new StudyDocumentEntityDTO());
        expectedDTOs.add(new StudyDocumentEntityDTO());
        when(documentMapper.studyDocumentsToDTOs(StudyDocuments)).thenReturn(expectedDTOs);

        // Call the method under test
        List<StudyDocumentEntityDTO> actualDTOs = entityService.getStudyDocuments(studyId);

        // Verify the results
        assertNotNull(actualDTOs);
        assertEquals(expectedDTOs.size(), actualDTOs.size());
        // You can add more assertions if needed
    }

    @Test
    void testGetStudyDocuments_InvalidStudyId() {
        Integer studyId = -1;
        LkupStatus approvedStatus = new LkupStatus(27, "Approved", "study");

        when(statusRepository.findByUsageAndName("study", "Approved")).thenReturn(approvedStatus);
        // Mock the repository method to return an empty list
        when(
            dataFileRepository.findByDataSubmission_StudyIdAndIsCurrentVersionTrueAndLookupDataFileCategoryCategoryGroup(
                studyId, "document")).thenReturn(Collections.emptyList());

        List<StudyDocumentEntityDTO> actualDocuments = entityService.getStudyDocuments(studyId);

        // Assert that the StudyNotFoundException is thrown when study does not exist
        assertEquals(Collections.emptyList(), entityService.getStudyDocuments(studyId));
    }

}
