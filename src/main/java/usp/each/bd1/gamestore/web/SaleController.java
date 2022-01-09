package usp.each.bd1.gamestore.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import usp.each.bd1.gamestore.data.entity.Cashier;
import usp.each.bd1.gamestore.data.entity.Sale;
import usp.each.bd1.gamestore.data.repository.CashierRepository;
import usp.each.bd1.gamestore.data.repository.SaleRepository;

@RestController
@RequestMapping("/sale")

public class SaleController {

    static class SaleRegisterPayload {
        List<String> barcodes;
        String customerCpf;
        String paymentMethod;
        int points;

        public SaleRegisterPayload(final List<String> barcodes, final String customerCpf, final String paymentMethod, final int points) {
            this.barcodes = barcodes;
            this.customerCpf = customerCpf;
            this.paymentMethod = paymentMethod;
            this.points = points;
        }
    }

    @Autowired
    private SaleRepository saleRepository;

    @RequestMapping("/register")
    @PostMapping
    public void registerSale(@RequestBody SaleRegisterPayload payload) throws DataIntegrityViolationException {
        var sale = new Sale();
        saleRepository.save(sale);
    }
}
