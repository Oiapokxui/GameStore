package usp.each.bd1.gamestore.web;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import usp.each.bd1.gamestore.data.repository.ItemRepository;
import usp.each.bd1.gamestore.data.repository.StorageRepository;

@Controller
@RequestMapping("/storages")
public class StorageController {
    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private StorageRepository storageRepository;

    @GetMapping
    public String getStorages(@RequestParam(value = "storage", required = false) String storageName, Model model) {
        var items = this.itemRepository.findByStorageName(Optional.ofNullable(storageName).orElse(""));
        var storages = this.storageRepository.findAll();
        model.addAttribute("items", items);
        model.addAttribute("storages", storages);
        return "storage";
    }
}
