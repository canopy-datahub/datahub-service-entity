package ex.org.project.entityservice.repository;

import ex.org.project.entityservice.model.Variable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VariableRepository extends JpaRepository<Variable, Integer> {

    List<Variable> findAllByCategoryId(Integer categoryId);

}
