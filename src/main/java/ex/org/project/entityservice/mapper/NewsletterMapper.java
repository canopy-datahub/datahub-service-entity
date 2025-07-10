package ex.org.project.entityservice.mapper;

import ex.org.project.entityservice.model.DTO.NewsletterDTO;
import ex.org.project.entityservice.model.Newsletter;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface NewsletterMapper {

    NewsletterDTO mapEntityToDto(Newsletter newsletter);

    List<NewsletterDTO> mapEntitiesToDtoList(List<Newsletter> newsletters);

}
