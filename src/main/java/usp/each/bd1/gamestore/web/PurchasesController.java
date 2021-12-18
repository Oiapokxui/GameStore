package usp.each.bd1.gamestore.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import usp.each.bd1.gamestore.data.entity.Purchase;
import usp.each.bd1.gamestore.data.repository.PurchaseRepository;

@RestController
@RequestMapping("/purchases")
public class PurchasesController {
    @Autowired
    private PurchaseRepository itemRepository;

    @GetMapping
    public Iterable<Purchase> getEmployees() {
        var items = this.itemRepository.findAll();
        return items;
    }
}
