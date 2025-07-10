package ex.org.project.entityservice.model.DTO;

import lombok.Data;

import java.util.List;

@Data
public class CombinedEventsDTO {
    private List<EventDTO> pastEvents;
    private List<EventDTO> currentEvents;
}
