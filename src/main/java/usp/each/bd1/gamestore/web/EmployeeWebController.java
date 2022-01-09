package usp.each.bd1.gamestore.web;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.Getter;
import usp.each.bd1.gamestore.data.entity.Item;
import usp.each.bd1.gamestore.data.repository.EmployeeRepository;
import usp.each.bd1.gamestore.data.repository.ItemRepository;
import usp.each.bd1.gamestore.data.repository.StorageRepository;

@Controller
@RequestMapping("/employee")
public class EmployeeWebController {

    static class ItemData {
        @Getter Item item;
        @Getter String type;
        @Getter String saleId;

        public ItemData(final Item item) {
            this.item = item;
            this.type = EmployeeWebController.translateTypeStringToPTBR(item);;
            this.saleId = item.getSaleIdAsString();
        }
    }

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private StorageRepository storageRepository;

    @GetMapping("/item")
    public String getItemsListPage(@RequestParam(value = "storage", required = false) String storageName, Model model) {
        var items = Optional.ofNullable(storageName)
                .flatMap((name) -> Optional.of(this.itemRepository.findByStorageName(name)))
                .orElse(this.itemRepository.findAll());

        var storages = this.storageRepository.findAll();
        var templates = items.stream().map(ItemData::new).toList();

        model.addAttribute("itemDatas", templates);
        model.addAttribute("storages", storages);
        return "employee-item-list";
    }

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

    private static String translateTypeStringToPTBR(Item item) {
        return switch(item.getItemType()) {
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
