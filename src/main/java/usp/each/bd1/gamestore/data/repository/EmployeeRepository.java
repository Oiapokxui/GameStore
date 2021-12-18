package usp.each.bd1.gamestore.data.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import usp.each.bd1.gamestore.data.entity.Employee;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, String> {
    @Query(value="select * from FUNCIONARIO", nativeQuery = true)
    List<Employee> findAll();

    @Query(value = "select * from funcionario where cpf = :cpf", nativeQuery = true)
    Optional<Employee> findById(@Param("cpf") String id);
}
