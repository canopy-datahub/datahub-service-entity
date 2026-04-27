package org.canopyplatform.canopy.entityservice.repository;

import org.canopyplatform.canopy.entityservice.model.VariablePermissibleValue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VariablePermissibleValueRepository extends JpaRepository<VariablePermissibleValue, Integer> {

    List<VariablePermissibleValue> findByVariableNameOrderByValue(String variableName);
}
