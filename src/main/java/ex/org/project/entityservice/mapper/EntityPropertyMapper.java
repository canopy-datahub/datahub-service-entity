package ex.org.project.entityservice.mapper;

import ex.org.project.entityservice.model.DTO.EntityPropertyDTO;
import ex.org.project.entityservice.model.EntityProperty;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EntityPropertyMapper {

    EntityPropertyDTO entityToDto(EntityProperty entityProperty);

    List<EntityPropertyDTO> entityListToDtoList(List<EntityProperty> entityPropertyList);

}
