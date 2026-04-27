package org.canopyplatform.canopy.entityservice.repository;

import org.canopyplatform.canopy.entityservice.model.VariableCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VariableCategoryRepository extends JpaRepository<VariableCategory, Integer> {

    Optional<VariableCategory> findByName(String name);

}
