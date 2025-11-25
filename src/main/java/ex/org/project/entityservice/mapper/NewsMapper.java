package ex.org.project.entityservice.mapper;

import ex.org.project.entityservice.model.DTO.NewsDTO;
import ex.org.project.entityservice.model.News;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface NewsMapper {

	// Homepage News Summary

	@Named("homepageNews")
	@Mapping(source = "type.name", target = "type")
    @Mapping(source = "description", target = "description", qualifiedByName = "splitDescription")
    NewsDTO newsToHomepageDTO(News news);

    @IterableMapping(qualifiedByName = "homepageNews")
    List<NewsDTO> newsToHomepageDTOs(List<News> news);

    @Named("splitDescription")
    static String splitDescription(String sourceDescription) {
        String[] result = sourceDescription.split("\\|\\|\\|");
        return result.length > 1 ? result[0].trim() + "..." : result[0].trim();
    }

    // All News page

    @Named("allNews")
    @Mapping(source = "type.name", target = "type")
    @Mapping(source = "description", target = "description", qualifiedByName = "splitNewsDescription")
    NewsDTO newsToAllNewsDTO(News news);

    @IterableMapping(qualifiedByName = "allNews")
    List<NewsDTO> newsToAllNewsDTOs(List<News> news);

    @Named("splitNewsDescription")
    static String splitNewsDescription(String sourceDescription){
    	String[] result = sourceDescription.split("\\|\\|\\|");
        return result.length > 2 ? result[0] + result[1].trim() + "..." : result[0] + result[1].trim();
    }

    // Individual news item page

    @Mapping(source = "type.name", target = "type")
    @Mapping(source = "description", target = "description", qualifiedByName = "fixDescription")
    NewsDTO newsToNewsDTO(News news);

    @Named("fixDescription")
    static String fixDescription(String sourceDescription){
        return sourceDescription.replace(" |||", "");
    }

}
