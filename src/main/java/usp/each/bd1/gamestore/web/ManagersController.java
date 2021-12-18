package usp.each.bd1.gamestore.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import usp.each.bd1.gamestore.data.entity.Item;
import usp.each.bd1.gamestore.data.entity.Manager;
import usp.each.bd1.gamestore.data.repository.ManagerRepository;

@RestController
@RequestMapping("/managers")
public class ManagersController {
    @Autowired
    private ManagerRepository managerRepository;

    @GetMapping
    public List<Manager> getEmployees() {
        var items = this.managerRepository.findAll();
        return items;
    }
}
