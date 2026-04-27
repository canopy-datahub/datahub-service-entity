package org.canopyplatform.canopy.entityservice.mapper;

import org.canopyplatform.canopy.entityservice.model.DTO.EntityPropertyDTO;
import org.canopyplatform.canopy.entityservice.model.EntityProperty;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EntityPropertyMapper {

    EntityPropertyDTO entityToDto(EntityProperty entityProperty);

    List<EntityPropertyDTO> entityListToDtoList(List<EntityProperty> entityPropertyList);

}
