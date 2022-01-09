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
import usp.each.bd1.gamestore.data.repository.SaleRepository;
import usp.each.bd1.gamestore.data.repository.StorageRepository;

@RestController
@RequestMapping("/item")
public class ItemController {
    static class EditItemPayload {
        Item item;
        String storageName;

        public EditItemPayload(final Item item, final String storageName) {
            this.item = item;
            this.storageName = storageName;
        }
    }

    @Autowired private ItemRepository itemRepository;
    @Autowired private StorageRepository storageRepository;
    @Autowired private SaleRepository saleRepository;

    @GetMapping("/all")
    public Iterable<Item> getItems() {
        return this.itemRepository.findAll();
    }

    @PostMapping("/create")
    public void createItem(@RequestBody EditItemPayload payload){
        var newItem = payload.item;

        assert(newItem.getPrice() >= 0);
        assert(!itemRepository.existsById(newItem.getBarcode()));

        var updatedStorage = this.storageRepository.findById(payload.storageName);

        newItem.setStorage(updatedStorage.orElse(null));
        this.itemRepository.save(newItem);
    }

    @PostMapping("/edit")
    public void updateItem(@RequestBody EditItemPayload payload){
        var updatedItem = payload.item;
        var existingItem = itemRepository.findById(updatedItem.getBarcode()).orElse(updatedItem);
        var updatedStorageOpt =  this.storageRepository.findById(payload.storageName);
        var updatedStorage = existingItem.getSale() == null ? null : updatedStorageOpt.orElse(null);

        existingItem.setName(updatedItem.getName());
        existingItem.setPrice(updatedItem.getPrice());
        existingItem.setStorage(updatedStorage);
        this.itemRepository.save(existingItem);
    }

    @PostMapping("/delete")
    public void deleteItem(@RequestParam("barcode") String barcode) {
        var item = itemRepository.findById(barcode);
        this.itemRepository.delete(item.orElse(new Item()));
    }

    @RequestMapping("/search/all")
    @PostMapping
    public List<Item> getItemsByName(@RequestBody final String itemName) {
        return this.itemRepository.findByName(itemName);
    }

    @RequestMapping("/search/any")
    @PostMapping
    public Item getAnyItemByName(@RequestBody final String itemName) {
        return getItemsByName(itemName).stream().findFirst().orElse(null);
    }

    @PostMapping()
    public Item getAnyItemByBarcode(@RequestParam("barcode") String barcode) throws Exception {
        return itemRepository.findById(barcode).orElseThrow(() -> new Exception("No item with barcode " + barcode +" found "));
    }

    public Iterable<Item> getItemsFromStorageObj(@RequestBody final Storage storage) {
        return this.itemRepository.findItemByStorageEquals(storage);
    }

    @RequestMapping("/search/all/storageName")
    @PostMapping
    public Iterable<Item> getItemsFromStorage(@RequestBody final String storageName) {
        return this.itemRepository.findByStorageName(storageName);
    }
}
