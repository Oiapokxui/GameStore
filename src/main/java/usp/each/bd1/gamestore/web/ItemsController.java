package usp.each.bd1.gamestore.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import usp.each.bd1.gamestore.data.entity.Item;
import usp.each.bd1.gamestore.data.repository.ItemRepository;

@RestController
@RequestMapping("/items")
public class ItemsController {
    @Autowired
    private ItemRepository itemRepository;

    @GetMapping
    public Iterable<Item> getEmployees() {
        var items = this.itemRepository.findAll();
        return items;
    }
}
