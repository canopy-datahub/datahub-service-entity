package ex.org.project.entityservice.mapper;

import ex.org.project.entityservice.model.DTO.PermissibleValueDTO;
import ex.org.project.entityservice.model.VariablePermissibleValue;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel="spring")
public interface VariablesMapper {

    PermissibleValueDTO mapToDto(VariablePermissibleValue variablePermissibleValue);

    List<PermissibleValueDTO> mapToDtoList(List<VariablePermissibleValue> variablePermissibleValueList);

}
