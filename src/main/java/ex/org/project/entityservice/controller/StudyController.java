package ex.org.project.entityservice.controller;

import java.util.List;
import java.util.Map;

import ex.org.project.entityservice.auth.*;
import ex.org.project.entityservice.model.DTO.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ex.org.project.entityservice.service.EntityService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/study")
public class StudyController {

    private final EntityService entityService;

    private final UserAuthService authService;

    @GetMapping("/getStudy")
    public ResponseEntity<StudyOverviewDTO> getStudy(
        @CookieValue(value = "chocolateChip", required = false) String sessionId,
        @RequestParam("studyId") Integer studyId
    ) {
        boolean isApprovedStudy = entityService.isApprovedStudy(studyId);
        if(!isApprovedStudy) {
            authService.checkAuth(sessionId, List.of(AccessRole.DATA_CURATOR));
        }
        // Calls the entityService to retrieve study properties
        StudyOverviewDTO study = entityService.getStudyOverview(studyId);
        return ResponseEntity.ok(study);
    }

    @GetMapping("/getDocuments")
    public ResponseEntity<List<StudyDocumentEntityDTO>> getDocuments(
        @CookieValue(value = "chocolateChip", required = false) String sessionId,
        @RequestParam("studyId") Integer studyId
    ) {
        boolean isApprovedStudy = entityService.isApprovedStudy(studyId);
        if(!isApprovedStudy) {
            authService.checkAuth(sessionId, List.of(AccessRole.DATA_CURATOR));
        }
        List<StudyDocumentEntityDTO> displaySettingsMap = entityService.getStudyDocuments(studyId);
        return ResponseEntity.ok(displaySettingsMap);
    }

    @GetMapping("/getDatasets")
    public ResponseEntity<DatasetDTO> getDatasets(
        @CookieValue(value = "chocolateChip", required = false) String sessionId,
        @RequestParam("studyId") Integer studyId
    ) {

        boolean isApprovedStudy = entityService.isApprovedStudy(studyId);
        //if study is not approved, only data curators should have access to datasets
        if(!isApprovedStudy) {
            authService.checkAuth(sessionId, List.of(AccessRole.DATA_CURATOR));
        }
        try {
            //checks if the user is valid to access datasets
            Integer userId = authService.checkAuth(sessionId);
            return new ResponseEntity<>(entityService.getDatasets(studyId, userId), HttpStatus.OK);
        }
        catch(UserAuthenticationException | UserNotFoundException e) {
            //If sessionId not provided or user does not have valid access to datasets, return approved datasets
            return new ResponseEntity<>(entityService.getDatasets(studyId), HttpStatus.OK);
        }
    }

    @GetMapping("/registrationCodelists")
    public ResponseEntity<Map<String, List<String>>> getStudyCodelists() {
        return ResponseEntity.ok(entityService.getStudyCodelists());
    }

    @GetMapping("/getRegistrationProperties")
    public ResponseEntity<List<EntityPropertyDTO>> getStudyRegistrationEntityProperties() {
        return ResponseEntity.ok(entityService.getStudyRegistrationEntityProperties());
    }

}
