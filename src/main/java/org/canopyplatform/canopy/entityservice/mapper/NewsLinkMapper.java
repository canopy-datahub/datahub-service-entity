package org.canopyplatform.canopy.entityservice.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.canopyplatform.canopy.entityservice.model.NewsLink;
import org.canopyplatform.canopy.entityservice.model.DTO.NewsLinkDTO;

import java.util.List;

@Mapper(componentModel = "spring")
public interface NewsLinkMapper {
    NewsLinkMapper INSTANCE = Mappers.getMapper(NewsLinkMapper.class);

    NewsLinkDTO newsLinkToNewsLinkDTO(NewsLink newsLink);

    List<NewsLinkDTO> toDTOs(List<NewsLink> newsLinks);
}
