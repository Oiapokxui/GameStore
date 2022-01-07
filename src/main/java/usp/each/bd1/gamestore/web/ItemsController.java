package usp.each.bd1.gamestore.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import usp.each.bd1.gamestore.data.entity.Item;
import usp.each.bd1.gamestore.data.entity.Storage;
import usp.each.bd1.gamestore.data.repository.ItemRepository;

@RestController
@RequestMapping("/items")
public class ItemsController {
    @Autowired
    private ItemRepository itemRepository;

    @GetMapping("/all")
    public Iterable<Item> getItems() {
        var items = this.itemRepository.findAll();
        return items;
    }

    @PostMapping("/edit")
    public void updateItem(@RequestBody Item item) {
        var storage = this.itemRepository.getStorageByBarcode(item.getBarcode());
        var sale = this.itemRepository.getSaleByBarcode(item.getBarcode());
        item.setStorage(storage.orElse(null));
        item.setSale(sale.orElse(null));
        this.itemRepository.save(item);
    }

    @RequestMapping("/search/all")
    @PostMapping
    public List<Item> getItemsByName(@RequestBody final String itemName) {
        return this.itemRepository.searchByName(itemName);
    }

    @RequestMapping("/search/any")
    @PostMapping
    public Item getAnyItemByName(@RequestBody final String itemName) {
        return getItemsByName(itemName).stream().findFirst().orElse(null);
    }

    @RequestMapping("/search/any/barcode")
    @PostMapping
    public Item getAnyItemByBarcode(@RequestParam("barcode") String barcode) {
        return itemRepository.findById(barcode).orElse(null);
    }

    public Iterable<Item> getItemsFromStorageObj(@RequestBody final Storage storage) {
        return this.itemRepository.findItemByStorageEquals(storage);
    }

    @RequestMapping("/search/all/storageName")
    @PostMapping
    public Iterable<Item> getItemsFromStorage(@RequestBody final String storageName) {
        var items = this.itemRepository.findByStorageName(storageName);
        return items;
    }
}
