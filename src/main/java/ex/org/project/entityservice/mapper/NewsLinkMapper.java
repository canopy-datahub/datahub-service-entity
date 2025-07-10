package ex.org.project.entityservice.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ex.org.project.entityservice.model.NewsLink;
import ex.org.project.entityservice.model.DTO.NewsLinkDTO;

import java.util.List;

@Mapper(componentModel = "spring")
public interface NewsLinkMapper {
    NewsLinkMapper INSTANCE = Mappers.getMapper(NewsLinkMapper.class);

    NewsLinkDTO newsLinkToNewsLinkDTO(NewsLink newsLink);

    List<NewsLinkDTO> toDTOs(List<NewsLink> newsLinks);
}
