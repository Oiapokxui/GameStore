package usp.each.bd1.gamestore.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
        var employees = this.employeeRepository.findAll();
        return employees;
    }

    @RequestMapping("/del")
    @PostMapping
    public void delete(@RequestBody String id) {
        this.employeeRepository.deleteById(id);
    }

    @RequestMapping("/rm-manager")
    @PostMapping
    public void removeManager(@RequestBody String id) {
        this.employeeRepository.updateManager(id);
        this.employeeRepository.deleteById(id);
    }

    @RequestMapping("/search-by-manager")
    @PostMapping
    public List<Employee> searchByManager(@RequestBody String id) {
        var employees = employeeRepository.findByManagerCpf(id);
        return employees;
    }
}
