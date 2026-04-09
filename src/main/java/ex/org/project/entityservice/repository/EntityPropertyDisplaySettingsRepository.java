package ex.org.project.entityservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ex.org.project.entityservice.model.EntityPropertyDisplaySettings;

    @Repository
    public interface EntityPropertyDisplaySettingsRepository extends JpaRepository<EntityPropertyDisplaySettings, Integer> {

        // Retrieves a list of EntityDTO objects by filtering on whether they are facets and the lookup entity type name, ordered by facet order.
        List<EntityPropertyDisplaySettings> findByIsFacetAndEntityProperty_LookupEntityType_NameAndPageOrderByFacetOrder(
                @Param("isFacet") Boolean isFacet,
                @Param("page") String page,
                @Param("entityProperty.lookupEntityType.name") String name
        );

        // Retrieves a list of EntityDTO objects by filtering on the page name and ordering them by display order.
        List<EntityPropertyDisplaySettings> findByPage_OrderByDisplayOrder(@Param("page") String page);


    }
