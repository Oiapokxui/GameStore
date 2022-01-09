package usp.each.bd1.gamestore.web;

import java.math.BigInteger;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.Getter;
import usp.each.bd1.gamestore.data.entity.Item;
import usp.each.bd1.gamestore.data.repository.CustommerRepository;
import usp.each.bd1.gamestore.data.repository.EmployeeRepository;
import usp.each.bd1.gamestore.data.repository.ItemRepository;
import usp.each.bd1.gamestore.data.repository.StorageRepository;

@Controller
@RequestMapping("/custommer")
public class CustommerWebController {

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
    private CustommerRepository custommerRepository;

    @Autowired
    private StorageRepository storageRepository;

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

    @GetMapping("/register")
    public String getRegisterEmployeePage() {
        return "manager-employee-register";
    }

    private String translateTypeStringToPTBR(String type) {
        return switch(type) {
            case "accessory" -> "Acessório";
            case "videoGame" -> "Video-Game";
            case "console" -> "Console";
            default -> "Desalocado";
        };
    }

   @GetMapping("reg-sale")
   public String getSaleRegisterPage() {
        return "employee-sale-register";
   }
}
