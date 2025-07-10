package ex.org.project.entityservice.repository;

import ex.org.project.entityservice.model.VariablePropertyValue;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VariablePropertyValueRepository extends JpaRepository<VariablePropertyValue, Long> {

    List<VariablePropertyValue> findByVariableIdAndPageOrderByDisplayOrder(Integer variableId, String page);

}
