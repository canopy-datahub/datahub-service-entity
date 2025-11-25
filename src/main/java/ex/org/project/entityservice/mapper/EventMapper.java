package ex.org.project.entityservice.mapper;

import ex.org.project.entityservice.model.DTO.EventDTO;
import ex.org.project.entityservice.model.Event;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EventMapper {
    EventMapper INSTANCE = Mappers.getMapper(EventMapper.class);

    @Named("homepageEvent")
    @Mapping(source = "eventType.name", target = "type")
    @Mapping(source = "description", target = "description", qualifiedByName = "splitDescription")
    EventDTO eventsToEventsDTO(Event event);

    @IterableMapping(qualifiedByName = "homepageEvent")
    List<EventDTO> eventsToHomepageDTOs(List<Event> events);

    @Named("splitDescription")
    public static String splitDescription(String sourceDescription) {
        String[] result = sourceDescription.split("\\|\\|\\|");
        return result.length > 1 ? result[0].trim() + "..." : result[0].trim();
    }


    @Named("allEvents")
    @Mapping(source = "eventType.name", target = "type")
    @Mapping(source = "description", target = "description", qualifiedByName = "fixDescription")
    EventDTO allEventsToEventsDTO(Event event);

    @IterableMapping(qualifiedByName = "allEvents")
    List<EventDTO> allToDTOs(List<Event> events);

    @Named("fixDescription")
    public static String fixDescription(String sourceDescription){
        return sourceDescription.replace(" |||", "");
    }
}
