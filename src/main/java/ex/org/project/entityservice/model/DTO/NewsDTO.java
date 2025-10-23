package ex.org.project.entityservice.model.DTO;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class NewsDTO {
    private Long id;
    private String slug;
    private String title;
    private String type;
    private String description;
    private Date startDate;
    private Date expirationDate;
    private Date createdAt;
    private List<NewsLinkDTO> links;
}
