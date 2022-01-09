package usp.each.bd1.gamestore.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import usp.each.bd1.gamestore.data.repository.ItemRepository;
import usp.each.bd1.gamestore.data.repository.StorageRepository;

@Controller
@RequestMapping("/item/page")
public class ItemWebController {
    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private StorageRepository storageRepository;

    @GetMapping("/edit")
    public String getEditItemPage(@RequestParam("barcode") String barcode, Model model) {
        var item = this.itemRepository.findById(barcode);
        var storages = this.storageRepository.findAll();
        model.addAttribute("item", item.orElse(null));
        model.addAttribute("storages", storages);
        return (item.isPresent()) ? "employee-item-edit" : null;
    }

    @GetMapping("/register")
    public String getRegisterItemPage(Model model) {
        var storages = this.storageRepository.findAll();
        model.addAttribute("storages", storages);
        return "employee-item-register";
    }
}
