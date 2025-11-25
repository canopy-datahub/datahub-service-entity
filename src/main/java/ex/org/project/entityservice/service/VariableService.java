package ex.org.project.entityservice.service;

import java.util.List;

import org.springframework.stereotype.Service;

import ex.org.project.entityservice.mapper.StudyMapper;
import ex.org.project.entityservice.mapper.VariablesMapper;
import ex.org.project.entityservice.model.StudyVariable;
import ex.org.project.entityservice.model.StudyView;
import ex.org.project.entityservice.model.Variable;
import ex.org.project.entityservice.model.VariablePermissibleValue;
import ex.org.project.entityservice.model.DTO.PermissibleValueDTO;
import ex.org.project.entityservice.model.DTO.StudyViewDTO;
import ex.org.project.entityservice.model.DTO.VariableDTO;
import ex.org.project.entityservice.repository.StudyEntityRepository;
import ex.org.project.entityservice.repository.StudyRepository;
import ex.org.project.entityservice.repository.VariablePermissibleValueRepository;
import ex.org.project.entityservice.repository.VariableRepository;
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
