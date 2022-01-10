package usp.each.bd1.gamestore.web;

import java.math.BigInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.Getter;
import usp.each.bd1.gamestore.data.entity.Item;
import usp.each.bd1.gamestore.data.repository.CustomerRepository;
import usp.each.bd1.gamestore.data.repository.StorageRepository;
import usp.each.bd1.gamestore.exception.CustomerNotFound;

@Controller
@RequestMapping("/customer")
public class CustomerWebController {

    static class ItemData {
        @Getter
        Item item;

        @Getter
        String type;

        @Getter
        BigInteger saleId;

        public ItemData(final Item item, final String type, final BigInteger saleId) {
            this.item = item;
            this.type = type;
            this.saleId = saleId;
        }
    }

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private StorageRepository storageRepository;

    @GetMapping("/edit")
    public String getCustomerEditPage(@RequestParam(value = "cpf") String cpf, Model model) throws CustomerNotFound {
        var customer = customerRepository.findById(cpf).orElseThrow(() -> new CustomerNotFound(""));
        model.addAttribute("customerName", customer.getName());
        model.addAttribute("customerCpf", customer.getCpf());
        return "employee-customer-edit";
    }

    @GetMapping("/register")
    public String getRegisterEmployeePage() {
        return "client-register";
    }

    private String translateTypeStringToPTBR(String type) {
        return switch(type) {
            case "accessory" -> "Acessório";
            case "videoGame" -> "Video-Game";
            case "console" -> "Console";
            default -> "Desalocado";
        };
    }
}
