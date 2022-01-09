package usp.each.bd1.gamestore.web;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import usp.each.bd1.gamestore.data.repository.EmployeeRepository;

@Controller
@RequestMapping("/")
public class HomepageWebController {

    @Autowired
    ManagerController managerController;

    @Autowired
    EmployeeRepository employeeRepository;

    @PostMapping
    @ResponseBody
    public String temp(@RequestParam("cpf") String cpf) throws Exception{
        if (!employeeRepository.existsById(cpf)) throw new NoSuchElementException("No cashier or manager found with " + cpf + " as cpf string");
        else if (managerController.isManager(cpf)) return managerHome();
        else return employeeHome();
    }

    @GetMapping
    public String login() {
       return "index";
    }

    @GetMapping("/manager-home")
    public String managerHome() {
        return "manager-home";
    }

    @GetMapping("/employee-home")
    public String employeeHome() {
        return "employee-home";
    }
}
