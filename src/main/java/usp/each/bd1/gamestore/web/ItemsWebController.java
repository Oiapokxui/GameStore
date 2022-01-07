package usp.each.bd1.gamestore.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import usp.each.bd1.gamestore.data.repository.ItemRepository;

@Controller
@RequestMapping("/items")
public class ItemsWebController {
    @Autowired
    private ItemRepository itemRepository;

    @GetMapping("/edit")
    public String getItems(@RequestParam("barcode") String barcode, Model model) {
        var item = this.itemRepository.findById(barcode);
        var returnTemplate = (item.isPresent()) ? "items-edit" : null;
        model.addAttribute("item", item.orElse(null));
        return returnTemplate;
    }
}
