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
        String managersCpf;

        @Getter
        String employeeType;

        public EmployeeData(final Employee employee) {
            this.employee = employee;
            this.name = employee.getName();
            this.employeeType = ManagerWebController.translateTypeStringToPTBR(employee);
            this.managersCpf = Optional.ofNullable(employee.getManager())
                    .flatMap( manager -> Optional.of(manager.getCpf()))
                    .orElse("");
        }
    }

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private StorageRepository storageRepository;

    private static String translateTypeStringToPTBR(Employee employee) {
        var type = employee.getEmployeeType();
        return switch(type) {
            case "manager" -> "Gerente";
            case "salesAssociate" -> "Atendente";
            case "technician" -> "Técnico de Manutenção";
            case "cashier" -> "Caixa";
            default -> "Desalocado";
        };
    }

    @GetMapping("/employee")
    public String getEmployeeListPage(Model model) {
        var employees = this.employeeRepository.findAll();
        var templates = employees.stream().map(EmployeeData::new).toList();
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
