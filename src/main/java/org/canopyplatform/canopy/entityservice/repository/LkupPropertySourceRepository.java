package org.canopyplatform.canopy.entityservice.repository;

import org.canopyplatform.canopy.entityservice.model.LkupPropertySource;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LkupPropertySourceRepository extends JpaRepository<LkupPropertySource, Integer> {

 List<LkupPropertySource> findAllByNameIn(List<String> sourceNames);

}
