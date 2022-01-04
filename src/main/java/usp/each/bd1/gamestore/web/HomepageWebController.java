package usp.each.bd1.gamestore.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/")
public class HomepageWebController {
    @Autowired
    ManagersController managersController;

    @Autowired
    CashierController cashierController;

    @PostMapping("/home")
    public String temp(@RequestParam("cpf") String cpf) {
        try {
            if (managersController.isManager(cpf)) return "manager-home";
            if (cashierController.isSalesAssociate(cpf)) return "shop-assistant-home";
        }
        catch(Exception e) {}
        return "index";
    }
    @GetMapping
    public String login() {
       return "index";
    }
}
