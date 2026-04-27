package org.canopyplatform.canopy.entityservice.mapper;

import java.util.Arrays;
import java.util.List;

import org.canopyplatform.canopy.entityservice.model.DTO.PropsDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import org.canopyplatform.canopy.entityservice.model.EntityPropertyDisplaySettings;
import org.canopyplatform.canopy.entityservice.model.DTO.EntityDTO;

@Mapper(componentModel = "spring")
public interface EntityDTOMapper {
	EntityDTOMapper INSTANCE = Mappers.getMapper(EntityDTOMapper.class);

	List<String> listProps = Arrays.asList("types", "subject", "source", "institutes_supporting_study", "topics", "study_population_focus", "study_variables");

	@Mapping(source = "entityProperty.name", target = "entityPropertyName", qualifiedByName = "listMapper")
	EntityDTO entityToEntityDTO(EntityPropertyDisplaySettings entity);

	List<EntityDTO> entitiesToEntityDTOs(List<EntityPropertyDisplaySettings> entities);

	@Named("listMapper")
	public static String mapListFieldsToArrayName(String entityPropertyName) {
		if (listProps.contains(entityPropertyName)) {
			return entityPropertyName + "_array";
		}
		return entityPropertyName;
	}

	@Mapping(source = "entityProperty.name", target = "entityPropertyName")
	PropsDTO propDTOto(EntityPropertyDisplaySettings entityPropertyDisplaySettings);

	List<PropsDTO> proptoDTOs(List<EntityPropertyDisplaySettings> props);

}
