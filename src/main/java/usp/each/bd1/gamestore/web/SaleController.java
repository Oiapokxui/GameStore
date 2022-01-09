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
import usp.each.bd1.gamestore.data.repository.CustomerRepository;
import usp.each.bd1.gamestore.data.repository.ItemRepository;
import usp.each.bd1.gamestore.data.repository.SaleRepository;
import usp.each.bd1.gamestore.exception.CustomerNotFound;
import usp.each.bd1.gamestore.exception.EmployeeNotFound;

@RestController
@RequestMapping("/sale")

public class SaleController {

    static class SaleRegisterPayload {
        List<String> barcodes;
        String customerCpf;
        String cashierCpf;
        String paymentMethod;
        int points;

        public SaleRegisterPayload(final List<String> barcodes, final String customerCpf, final String cashierCpf, final String paymentMethod,
                final int points) {
            this.barcodes = barcodes;
            this.customerCpf = customerCpf;
            this.cashierCpf = cashierCpf;
            this.paymentMethod = paymentMethod;
            this.points = points;
        }
    }

    @Autowired
    private SaleRepository saleRepository;

    @Autowired
    private CashierRepository cashierRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ItemRepository itemRepository;

    @RequestMapping("/register")
    @PostMapping
    public void registerSale(@RequestBody SaleRegisterPayload payload) throws EmployeeNotFound, CustomerNotFound {
        var sale = new Sale();
        var customer =  customerRepository.findById(payload.customerCpf).orElseThrow( () -> new CustomerNotFound(""));
        var cashier =  cashierRepository.findById(payload.cashierCpf).orElseThrow( () -> new EmployeeNotFound(""));
        sale.setAttributedPoints(payload.points);
        sale.setPaymentMethod(payload.paymentMethod);
        sale.setBuyer(customer);
        sale.setCashier(cashier);
        saleRepository.save(sale);
        sale.getId();
        itemRepository.updateSaleId(sale.getId(), payload.barcodes);
    }
}
