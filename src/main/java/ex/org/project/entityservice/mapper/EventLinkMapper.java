package ex.org.project.entityservice.mapper;

import ex.org.project.entityservice.model.DTO.EventLinkDTO;
import ex.org.project.entityservice.model.EventLink;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EventLinkMapper {
    EventLinkMapper INSTANCE = Mappers.getMapper(EventLinkMapper.class);

    EventLinkDTO eventLinkToEventLinkDTO(EventLink eventLink);

    List<EventLinkDTO> toDTOs(List<EventLink> eventLinks);
}
