package usp.each.bd1.gamestore.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import usp.each.bd1.gamestore.data.repository.StorageRepository;

@Controller
@RequestMapping("/storage")
public class StorageWebController {

    @Autowired
    private StorageRepository storageRepository;

    @GetMapping("/edit")
    public String getStorageEditPage(@RequestParam("name") String name, Model model) {
        var storage = this.storageRepository.findById(name);
        model.addAttribute("storage", storage.orElse(null));
        return (storage.isPresent()) ? "manager-storage-edit" : null;
    }
}
