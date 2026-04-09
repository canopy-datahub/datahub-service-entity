package ex.org.project.entityservice.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import ex.org.project.entityservice.model.EventLink;
import ex.org.project.entityservice.model.DTO.EventLinkDTO;

@Mapper(componentModel = "spring")
public interface EventLinkMapper {
    EventLinkMapper INSTANCE = Mappers.getMapper(EventLinkMapper.class);

    EventLinkDTO eventLinkToEventLinkDTO(EventLink eventLink);
    
    List<EventLinkDTO> toDTOs(List<EventLink> eventLinks);
}
