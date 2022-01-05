package usp.each.bd1.gamestore.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import usp.each.bd1.gamestore.data.entity.Cashier;
import usp.each.bd1.gamestore.data.repository.CashierRepository;

@RestController
@RequestMapping("/cashier")

public class CashierController {
    @Autowired
    private CashierRepository cashierRepository;

    @GetMapping
    public List<Cashier> getCashiers() {
        var items = this.cashierRepository.findAll();
        return items;
    }

    @RequestMapping("/search/any")
    @PostMapping
    public Optional<Cashier> searchByCashier(@RequestBody String cpf) throws Exception {
        var cashier = cashierRepository.findById(cpf);
        return cashier;
    }

    public boolean isCashier(@RequestParam("cpf") String cpf) throws Exception {return searchByCashier(cpf).isPresent();}
}
