package org.canopyplatform.canopy.entityservice.util;

import java.util.List;

public interface Constants {

    String PAGE_STUDY_SEARCH = "study_search";
    String PAGE_VARIABLE_SEARCH = "variable_search";
    String VARIABLE_ENTITY_NAME = "variable";
    String PAGE_STUDY_OVERVIEW = "study_overview";
    String PAGE_VARIABLE_OVERVIEW = "variable_overview";
    String STUDY_ENTITY_NAME = "study";

    String NEWS_GENERAL = "general news";
    String NEWS_FUNDING = "funding opportunities";

    String CATEGORY_DOCUMENT = "document";
    String CATEGORY_DATA = "data";

    String STATUS_APPROVED = "approved";

    String PROPERTY_CODELISTED = "codelisted";

    List<String> PROPERTY_SOURCE_STUDY_REG = List.of("dbGaP/MTA", "Online Submission");
}
