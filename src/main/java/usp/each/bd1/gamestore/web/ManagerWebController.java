package usp.each.bd1.gamestore.web;

import java.util.Optional;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.Getter;
import usp.each.bd1.gamestore.data.entity.Employee;
import usp.each.bd1.gamestore.data.repository.EmployeeRepository;
import usp.each.bd1.gamestore.data.repository.StorageRepository;

@Controller
@RequestMapping("/manager")
public class ManagerWebController {

    static class EmployeeData {
        @Getter
        Employee employee;

        @Getter
        String name;

        @Getter
        String employeeType;

        public EmployeeData(final Employee employee, final String employeeType) {
            this.employee = employee;
            this.employeeType = employeeType;
            this.name = employee.getThisPerson().getName();
        }
    }

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private StorageRepository storageRepository;

    private String translateTypeStringToPTBR(String type) {
        if(type.equals("manager")) return "Gerente";
        else if(type.equals("salesAssociate")) return "Atendente";
        else if(type.equals("technician")) return "Técnico de Manutenção";
        else if(type.equals("cashier")) return "Caixa";
        else return "Desalocado";
    }

    @GetMapping("/employee")
    public String getEmployeeListPage(Model model) {
        var employees = this.employeeRepository.findAll();
        Function<Employee, String> translateEmployeeType = (employee) -> translateTypeStringToPTBR(employee.getEmployeeType());
        var templates = employees.stream().map(employee -> new EmployeeData(employee, translateEmployeeType.apply(employee))).toList();
        model.addAttribute("employeeDatas", templates);
        return "manager-employee-list";
    }

    @GetMapping("/storage")
    public String getStorageListPage(Model model) {
        var storages = this.storageRepository.findAll();

        model.addAttribute("storages", storages);
        return "manager-storage-list";
    }
}
