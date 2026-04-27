package org.canopyplatform.canopy.entityservice.service;

import java.util.List;

import org.springframework.stereotype.Service;

import org.canopyplatform.canopy.entityservice.mapper.StudyMapper;
import org.canopyplatform.canopy.entityservice.mapper.VariablesMapper;
import org.canopyplatform.canopy.entityservice.model.StudyVariable;
import org.canopyplatform.canopy.entityservice.model.StudyView;
import org.canopyplatform.canopy.entityservice.model.Variable;
import org.canopyplatform.canopy.entityservice.model.VariablePermissibleValue;
import org.canopyplatform.canopy.entityservice.model.DTO.PermissibleValueDTO;
import org.canopyplatform.canopy.entityservice.model.DTO.StudyViewDTO;
import org.canopyplatform.canopy.entityservice.model.DTO.VariableDTO;
import org.canopyplatform.canopy.entityservice.repository.StudyEntityRepository;
import org.canopyplatform.canopy.entityservice.repository.StudyRepository;
import org.canopyplatform.canopy.entityservice.repository.VariablePermissibleValueRepository;
import org.canopyplatform.canopy.entityservice.repository.VariableRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class VariableService {

    private final VariablePermissibleValueRepository permissibleValueRepository;

    private final StudyRepository studyRepository;

    private final StudyMapper studyMapper;

    private final VariablesMapper variableMapper;

    private final VariableRepository variableRepository;

    private final StudyEntityRepository studyEntityRepository;

    public List<PermissibleValueDTO> getPermissibleValues(Integer variableId) {
        // Look up the variable name using the variable_id
        Variable variable = variableRepository.findById(variableId)
            .orElseThrow(() -> new RuntimeException("Variable not found with id: " + variableId));

        // Use the variable name to search in permissibleValueRepository
        List<VariablePermissibleValue> permissibleValues = permissibleValueRepository
            .findByVariableNameOrderByValue(variable.getName());

        return variableMapper.mapToDtoList(permissibleValues);
    }

    public List<StudyViewDTO> getLinkedStudies(Integer variableId) {
        List<StudyView> studies = studyRepository.findAllByVariableId(variableId);
        return studyMapper.toStudyDTOs(studies);
    }

    public List<VariableDTO> getAllVariables() {
        List<Variable> variables = variableRepository.findAll();
        return variables.stream().map(VariableDTO::new).toList();
    }

    public List<VariableDTO> getVariables(Integer studyId) {
        List<StudyVariable> variables = studyEntityRepository.findAllVariablesByStudyId(studyId);
        return variables.stream().map(VariableDTO::new).toList();
    }

}
