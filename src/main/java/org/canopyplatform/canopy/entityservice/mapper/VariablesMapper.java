package org.canopyplatform.canopy.entityservice.mapper;

import org.canopyplatform.canopy.entityservice.model.DTO.PermissibleValueDTO;
import org.canopyplatform.canopy.entityservice.model.VariablePermissibleValue;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel="spring")
public interface VariablesMapper {

    PermissibleValueDTO mapToDto(VariablePermissibleValue variablePermissibleValue);

    List<PermissibleValueDTO> mapToDtoList(List<VariablePermissibleValue> variablePermissibleValueList);

}
