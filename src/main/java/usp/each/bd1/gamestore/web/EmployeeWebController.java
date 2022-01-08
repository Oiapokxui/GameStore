package usp.each.bd1.gamestore.web;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import usp.each.bd1.gamestore.data.repository.EmployeeRepository;
import usp.each.bd1.gamestore.data.repository.ItemRepository;
import usp.each.bd1.gamestore.data.repository.StorageRepository;

@Controller
@RequestMapping("/employee")
public class EmployeeWebController {
    @Autowired
    private EmployeeRepository employeeRepository;


    @GetMapping("/edit")
    public String getEmployeeEditPage(@RequestParam(value = "cpf") String cpf, Model model) {
        var items = this.employeeRepository.findById(cpf);
        assert items.isPresent();
        var manager = Optional.ofNullable(items.get().getManager());
        var managerCpf = manager.map(value -> value.getThisEmployee().getCpf()).orElse("");

        model.addAttribute("name", items.get().getThisPerson().getName());
        model.addAttribute("employee", items.get());
        model.addAttribute("employeeManagerCpf", managerCpf);
        model.addAttribute("employeeType", items.get().getEmployeeType());
        return "manager-employee-edit";
    }
}
