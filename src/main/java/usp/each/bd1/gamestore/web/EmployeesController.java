package usp.each.bd1.gamestore.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import usp.each.bd1.gamestore.data.entity.Employee;
import usp.each.bd1.gamestore.data.repository.EmployeeRepository;

@RestController
@RequestMapping("/employees")
public class EmployeesController {
    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping
    public Iterable<Employee> getEmployees() {
        return this.employeeRepository.findAll();
    }
}
