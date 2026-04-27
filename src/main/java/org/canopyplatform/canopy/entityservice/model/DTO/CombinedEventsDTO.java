package org.canopyplatform.canopy.entityservice.model.DTO;

import lombok.Data;

import java.util.List;

@Data
public class CombinedEventsDTO {
    private List<EventDTO> pastEvents;
    private List<EventDTO> currentEvents;
}
