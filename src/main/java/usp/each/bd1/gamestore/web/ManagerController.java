package usp.each.bd1.gamestore.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import usp.each.bd1.gamestore.data.entity.Employee;
import usp.each.bd1.gamestore.data.entity.Manager;
import usp.each.bd1.gamestore.data.repository.ManagerRepository;

@RestController
@RequestMapping("/managers")

public class ManagerController {
    @Autowired
    private ManagerRepository managerRepository;

    @GetMapping
    public List<Manager> getManagers() {
        var items = this.managerRepository.findAll();
        return items;
    }

    @RequestMapping("/search/any")
    @PostMapping
    public Optional<Manager> searchAnyManager(@RequestParam("cpf") String cpf) throws Exception {
        var manager = managerRepository.findById(cpf);
        return manager;
    }

    @PostMapping
    public void create() {
        Manager my = new Manager("1003", new Employee("1003", "", "1003", 1.0));
        managerRepository.save(my);
    }

    public boolean isManager(@RequestParam("cpf") String cpf) throws Exception {return searchAnyManager(cpf).isPresent();}
}
