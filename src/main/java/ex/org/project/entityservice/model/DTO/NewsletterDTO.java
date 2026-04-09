package ex.org.project.entityservice.model.DTO;

import java.time.LocalDate;

public record NewsletterDTO(
        Integer id,
        String title,
        String url,
        LocalDate releaseDate
) {}
