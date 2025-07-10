package ex.org.project.entityservice.model.DTO;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Data;

@Data
public class EventDTO {

    private Long id;
    private String title;
    private String description;
    private String slug;    
    private String type;
    private String registrationUrl;
    private LocalDateTime eventDate;
    private LocalDateTime expirationDate;
    private LocalDateTime createdAt;
    private List<EventLinkDTO> links;
}
