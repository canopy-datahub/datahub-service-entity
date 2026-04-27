package org.canopyplatform.canopy.entityservice.mapper;

import org.canopyplatform.canopy.entityservice.model.DTO.NewsletterDTO;
import org.canopyplatform.canopy.entityservice.model.Newsletter;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface NewsletterMapper {

    NewsletterDTO mapEntityToDto(Newsletter newsletter);

    List<NewsletterDTO> mapEntitiesToDtoList(List<Newsletter> newsletters);

}
