package ex.org.project.entityservice.repository;

import ex.org.project.entityservice.model.VariablePermissibleValue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VariablePermissibleValueRepository extends JpaRepository<VariablePermissibleValue, Integer> {

    List<VariablePermissibleValue> findByVariableIdOrderByValue(Integer variableId);

}
