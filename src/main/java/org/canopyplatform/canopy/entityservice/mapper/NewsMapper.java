package org.canopyplatform.canopy.entityservice.mapper;

import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.canopyplatform.canopy.entityservice.model.News;
import org.canopyplatform.canopy.entityservice.model.DTO.NewsDTO;
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
        if (result.length > 2) {
            return result[0] + result[1].trim() + "...";
        } else if (result.length > 1) {
            return result[0] + result[1].trim();
        } else {
            return result[0].trim();
        }
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
