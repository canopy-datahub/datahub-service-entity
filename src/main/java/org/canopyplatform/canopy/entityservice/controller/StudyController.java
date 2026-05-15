package org.canopyplatform.canopy.entityservice.controller;

import java.util.List;
import java.util.Map;

import org.canopyplatform.canopy.entityservice.auth.UserAuthenticationException;
import org.canopyplatform.canopy.entityservice.auth.UserNotFoundException;
import org.canopyplatform.canopy.entityservice.auth.core.KeycloakAuthenticationService;
import org.canopyplatform.canopy.entityservice.auth.*;
import org.canopyplatform.canopy.entityservice.model.DTO.*;
import org.canopyplatform.canopy.entityservice.service.StudyAccessService;
import org.canopyplatform.canopy.entityservice.model.DTO.DatasetDTO;
import org.canopyplatform.canopy.entityservice.model.DTO.EntityPropertyDTO;
import org.canopyplatform.canopy.entityservice.model.DTO.StudyDocumentEntityDTO;
import org.canopyplatform.canopy.entityservice.model.DTO.StudyOverviewDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import org.canopyplatform.canopy.entityservice.service.EntityService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/study")
public class StudyController {

    private final EntityService entityService;

    private final KeycloakAuthenticationService authenticationService;

    private final StudyAccessService studyAccessService;

    @GetMapping("/getStudy")
    public ResponseEntity<StudyOverviewDTO> getStudy(
        @AuthenticationPrincipal Jwt jwt,
        @RequestParam("studyId") Integer studyId
    ) {
        // Read gate is access_level + Creator + role overrides (Curator/Admin
        // see everything). Approval status is no longer involved here —
        // status and access_level are orthogonal dimensions.
        studyAccessService.requireRead(jwt, studyId);
        StudyOverviewDTO study = entityService.getStudyOverview(studyId);
        return ResponseEntity.ok(study);
    }

    @GetMapping("/getDocuments")
    public ResponseEntity<List<StudyDocumentEntityDTO>> getDocuments(
        @AuthenticationPrincipal Jwt jwt,
        @RequestParam("studyId") Integer studyId
    ) {
        studyAccessService.requireRead(jwt, studyId);
        List<StudyDocumentEntityDTO> displaySettingsMap = entityService.getStudyDocuments(studyId);
        return ResponseEntity.ok(displaySettingsMap);
    }

    @GetMapping("/getDatasets")
    public ResponseEntity<DatasetDTO> getDatasets(
        @AuthenticationPrincipal Jwt jwt,
        @RequestParam("studyId") Integer studyId
    ) {
        studyAccessService.requireRead(jwt, studyId);
        try {
            // checks if the user is valid to access datasets (per-user tracking)
            Integer userId = authenticationService.checkAuth(jwt);
            return new ResponseEntity<>(entityService.getDatasets(studyId, userId), HttpStatus.OK);
        }
        catch(UserAuthenticationException | UserNotFoundException e) {
            // Anonymous reads of PUBLIC studies — fall through to the
            // user-agnostic dataset path.
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
