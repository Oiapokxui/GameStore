package usp.each.bd1.gamestore.data.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import usp.each.bd1.gamestore.data.entity.Employee;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, String> {
}
