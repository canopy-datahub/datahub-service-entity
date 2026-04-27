package org.canopyplatform.canopy.entityservice.controller;

import org.canopyplatform.canopy.entityservice.model.DTO.PermissibleValueDTO;
import org.canopyplatform.canopy.entityservice.model.DTO.PropertyValueDTO;
import org.canopyplatform.canopy.entityservice.model.DTO.StudyViewDTO;
import org.canopyplatform.canopy.entityservice.service.PropertyValueService;
import org.canopyplatform.canopy.entityservice.service.VariableService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/variable")
public class VariableController {

    private final PropertyValueService propertyValueService;

    private final VariableService variableService;

    @GetMapping("/overview")
    public ResponseEntity<Map<String, List<PropertyValueDTO>>> getVariableOverview(
        @RequestParam("variableId") Integer variableId
    ) {
        Map<String, List<PropertyValueDTO>> displaySettingsMap = propertyValueService.getVariableProps(variableId);
        return ResponseEntity.ok(displaySettingsMap);
    }

    @GetMapping("/permissibleValues")
    public ResponseEntity<List<PermissibleValueDTO>> getVariablePermissibleValues(
        @RequestParam("variableId") Integer variableId
    ) {
        List<PermissibleValueDTO> permissibleValues = variableService.getPermissibleValues(variableId);
        return ResponseEntity.ok(permissibleValues);
    }

    @GetMapping("/linkedStudies")
    public ResponseEntity<List<StudyViewDTO>> getVariableLinkedStudies(@RequestParam("variableId") Integer variableId) {
        List<StudyViewDTO> linkedStudies = variableService.getLinkedStudies(variableId);
        return ResponseEntity.ok(linkedStudies);
    }

}
