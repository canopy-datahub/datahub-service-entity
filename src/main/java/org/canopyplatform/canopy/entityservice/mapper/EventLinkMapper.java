package org.canopyplatform.canopy.entityservice.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import org.canopyplatform.canopy.entityservice.model.EventLink;
import org.canopyplatform.canopy.entityservice.model.DTO.EventLinkDTO;

@Mapper(componentModel = "spring")
public interface EventLinkMapper {
    EventLinkMapper INSTANCE = Mappers.getMapper(EventLinkMapper.class);

    EventLinkDTO eventLinkToEventLinkDTO(EventLink eventLink);

    List<EventLinkDTO> toDTOs(List<EventLink> eventLinks);
}
