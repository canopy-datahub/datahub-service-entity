package org.canopyplatform.canopy.entityservice.repository;

import org.canopyplatform.canopy.entityservice.model.VariablePropertyValue;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VariablePropertyValueRepository extends JpaRepository<VariablePropertyValue, Long> {

    List<VariablePropertyValue> findByVariableIdAndPageOrderByDisplayOrder(Integer variableId, String page);

}
