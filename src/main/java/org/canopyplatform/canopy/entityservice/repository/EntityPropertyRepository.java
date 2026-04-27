package org.canopyplatform.canopy.entityservice.repository;

import org.canopyplatform.canopy.entityservice.model.EntityProperty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EntityPropertyRepository extends JpaRepository<EntityProperty, Integer> {

    List<EntityProperty> findAllByPropertyType_Name(String propertyName);

    List<EntityProperty> findAllByPropertySourceIdIn(List<Integer> propertySourceIds);

}
