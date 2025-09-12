package ex.org.project.entityservice.service;

import java.util.*;
import java.util.stream.Collectors;

import ex.org.project.entityservice.auth.UserAuthService;
import ex.org.project.entityservice.auth.UserAuthorizationException;
import ex.org.project.entityservice.auth.UserNotFoundException;
import ex.org.project.entityservice.exception.ResourceNotFoundException;
import ex.org.project.entityservice.exception.custom.StatusNotFoundException;
import ex.org.project.entityservice.exception.custom.StudyNotFoundException;
import ex.org.project.entityservice.mapper.*;
import ex.org.project.entityservice.model.*;
import ex.org.project.entityservice.model.DTO.*;
import ex.org.project.entityservice.repository.*;
import org.springframework.stereotype.Service;

import static ex.org.project.entityservice.util.Constants.*;

import lombok.RequiredArgsConstructor;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class EntityService {

    private final EntityPropertyDisplaySettingsRepository entityPropertyDisplaySettingsRepository;

    private final LkupPropertyCodelistValueRepository codelistValueRepository;

    private final LkupPropertySourceRepository propertySourceRepository;

    private final EntityPropertyRepository entityPropertyRepository;

    private final DataFileRepository dataFileRepository;

    private final StudyRepository studyRepository;

    private final LkupStatusRepository statusRepository;

    private final DataFileMapper dataFileMapper;

    private final EntityDTOMapper entityDTOMapper;

    private final StudyDocumentMapper documentMapper;

    private final EntityPropertyMapper entityPropertyMapper;

    private final UserAuthService userAuthService;

    private final PropertyValueService propertyValueService;

    private final StudyEntityRepository studyEntityRepository;

    public List<EntityDTO> getDisplaySettingsByEntityType(String type) {
        List<EntityPropertyDisplaySettings> displaySettings = List.of();
        // Retrieves the display settings for facets related to the lookup entity type, ordered by facet order.
        switch (type) {
            case "study" -> displaySettings = entityPropertyDisplaySettingsRepository.findByIsFacetAndEntityProperty_LookupEntityType_NameAndPageOrderByFacetOrder(
                    true, STUDY_ENTITY_NAME, PAGE_STUDY_SEARCH);
            case "variable" -> displaySettings = entityPropertyDisplaySettingsRepository.findByIsFacetAndEntityProperty_LookupEntityType_NameAndPageOrderByFacetOrder(
                    true, VARIABLE_ENTITY_NAME, PAGE_VARIABLE_SEARCH);
        }
        return entityDTOMapper.entitiesToEntityDTOs(displaySettings);
    }

    public Map<String, List<PropsDTO>> getProps() {
        List<EntityPropertyDisplaySettings> displaySettingsList = entityPropertyDisplaySettingsRepository.findByPage_OrderByDisplayOrder(
            PAGE_STUDY_SEARCH);

        List<PropsDTO> propsDTOs = entityDTOMapper.proptoDTOs(displaySettingsList);

        List<PropsDTO> filteredPropsDTOs = propsDTOs.stream()
                                                    .filter(dto -> dto.getDisplaySection() != null &&
                                                                   !dto.getDisplaySection().equals("null"))
                                                    .toList();

        Map<String, List<PropsDTO>> propsMap = filteredPropsDTOs.stream()
                                                                .collect(
                                                                    groupingBy(PropsDTO::getDisplaySection, toList()));

        return propsMap;
    }

    /**
     * Checks if study is approved or not
     *
     * @return boolean of whether study is approved
     */
    public boolean isApprovedStudy(Integer studyId) {
        StudyView study = studyRepository.findById(studyId)
                                         .orElseThrow(
                                             () -> new StudyNotFoundException("No study found with ID: " + studyId));
        LkupStatus approvedStatus = statusRepository.findByUsageAndName("study", "Approved");
        if(approvedStatus == null) {
            throw new StatusNotFoundException("Could not find study status entity");
        }
        return study.getStatus().equals(approvedStatus.getName());
    }

    private Map<String, Object> convertPropertyValuetoList(StudyEntityDTO studyEntityDTO) {
        Map<String, Object> propertyMap = new HashMap<>();
        propertyMap.put("label", studyEntityDTO.getDisplayLabel());
        propertyMap.put("propertyValue", Arrays.asList(studyEntityDTO.getPropertyValue()));
        return propertyMap;
    }

    public List<StudyDocumentEntityDTO> getStudyDocuments(Integer studyId) {
        List<DataFile> studyDocuments = dataFileRepository.findByDataSubmission_StudyIdAndIsCurrentVersionTrueAndLookupDataFileCategoryCategoryGroup(
            studyId, "document");

        return documentMapper.studyDocumentsToDTOs(studyDocuments);
    }

    /**
     * Returns approved datasets associated with study and user access
     *
     * @param studyId is the id of study
     * @param userId  is the userId associated with valid session
     * @return DatasetDTO with boolean of whether user has access and list of datafileDTOs
     */
    public DatasetDTO getDatasets(Integer studyId, Integer userId) {
        List<DataFile> files = dataFileRepository.findAllByDataSubmission_StudyIdAndLookupDataFileCategory_CategoryGroupAndStatus_NameAndIsCurrentVersionTrue(
            studyId, CATEGORY_DATA, STATUS_APPROVED);
        files.forEach(this::checkIfSasAvailable);
        DatasetDTO datasetDTO = new DatasetDTO();
        //userAuthService.checkStudyAuthorization checks is user has been granted access to the study
        try {
            datasetDTO.setUserHasStudyAccess(userAuthService.checkStudyAuthorization(userId, studyId));
            //if a UserAuthorizationException is thrown, user does not have valid authorizations for study.
        }
        catch(UserAuthorizationException | UserNotFoundException e) {
            datasetDTO.setUserHasStudyAccess(false);
        }
        datasetDTO.setDataFileDTOS(dataFileMapper.dataFileListToDtoList(files));
        return datasetDTO;
    }

    /**
     * Returns approved datasets associated with study
     * Used if sessionId is invalid or not provided
     *
     * @param studyId is the id of study
     * @return DatasetDTO with user access false and list of datafileDTOs
     */
    public DatasetDTO getDatasets(Integer studyId) {
        //get all the approved datasets in study
        List<DataFile> files = dataFileRepository.findAllByDataSubmission_StudyIdAndLookupDataFileCategory_CategoryGroupAndStatus_NameAndIsCurrentVersionTrue(
            studyId, CATEGORY_DATA, STATUS_APPROVED);
        files.forEach(this::checkIfSasAvailable);
        DatasetDTO datasetDTO = new DatasetDTO();
        datasetDTO.setUserHasStudyAccess(false);
        datasetDTO.setDataFileDTOS(dataFileMapper.dataFileListToDtoList(files));
        return datasetDTO;
    }

    private void checkIfSasAvailable(DataFile dataFile) {
        boolean hasSas = dataFileRepository.existsSasFileForDataFile(dataFile.getId());
        dataFile.setSasAvailable(hasSas);
    }

    /**
     * Returns a map of all codelist values
     *
     * @return A mapping of a codelist entry name to a list of all possible values for that entry
     */
    public Map<String, List<String>> getStudyCodelists() {
        List<EntityProperty> properties = entityPropertyRepository.findAllByPropertyType_Name(PROPERTY_CODELISTED);
        List<Integer> codelistIds = properties.stream().map(EntityProperty::getCodeListId).toList();
        List<LkupPropertyCodelistValue> codelistValues = codelistValueRepository.findAllByPropertyCodelist_IdIn(
            codelistIds);

        Map<String, List<String>> codelist = codelistValues.stream()
                                                           .collect(groupingBy(
                                                               LkupPropertyCodelistValue::getPropertyCodelist,
                                                               Collectors.mapping(LkupPropertyCodelistValue::getValue,
                                                                                  toList()
                                                               )
                                                           ))
                                                           .entrySet()
                                                           .stream()
                                                           .collect(Collectors.toMap(entry -> entry.getKey().getName(),
                                                                                     entry -> entry.getValue()
                                                           ));
        return codelist;
    }

    /**
     * Return a list of entity properties that correspond to the study registration process
     *
     * @return list of entity property ids and names
     */
    public List<EntityPropertyDTO> getStudyRegistrationEntityProperties() {
        List<LkupPropertySource> propertySources = propertySourceRepository.findAllByNameIn(PROPERTY_SOURCE_STUDY_REG);
        if(propertySources.size() != 2) {
            throw new ResourceNotFoundException("Could not find corresponding study registration property sources");
        }
        List<Integer> sourceIds = propertySources.stream().map(LkupPropertySource::getId).toList();
        List<EntityProperty> studyRegistrationEntityProperties = entityPropertyRepository.findAllByPropertySourceIdIn(
            sourceIds);
        return entityPropertyMapper.entityListToDtoList(studyRegistrationEntityProperties);
    }

    public StudyOverviewDTO getStudyOverview(Integer studyId) {
        Map<String, List<PropertyValueDTO>> displaySettingsMap = propertyValueService.getStudyProps(studyId);
        List<StudyVariable> studyVariables = studyEntityRepository.findAllVariablesByStudyId(studyId);
        List<VariableDTO> variableDTOs = studyVariables.stream().map(VariableDTO::new).toList();
        return new StudyOverviewDTO(displaySettingsMap, variableDTOs);
    }

}
