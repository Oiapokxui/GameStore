package usp.each.bd1.gamestore.web;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import usp.each.bd1.gamestore.data.entity.Storage;
import usp.each.bd1.gamestore.data.repository.ItemRepository;
import usp.each.bd1.gamestore.data.repository.StorageRepository;

@RestController
@RequestMapping("/storage")
public class StorageController {
    @Autowired private StorageRepository storageRepository;
    @Autowired private ItemRepository itemRepository;

    @PostMapping("/edit")
    public void updateStorage(@RequestBody Map<String, String> names) {
        var newStorageName = names.get("newName");
        var oldStorageName = names.get("oldName");

        if (newStorageName.equals(oldStorageName)) return;
        if (storageRepository.findById(newStorageName).isEmpty()) this.storageRepository.save(new Storage(newStorageName));
        this.itemRepository.updateStorageName(newStorageName, oldStorageName);
        this.storageRepository.deleteById(oldStorageName);
    }

    @PostMapping("/delete")
    public void deleteStorage(@RequestParam("name") String storageName) throws DataIntegrityViolationException {
        try {
            this.storageRepository.deleteById(storageName);
        }
        catch(Exception e) {
            throw new DataIntegrityViolationException("");
        }
    }

    @PostMapping("/create")
    public void createStorage(@RequestBody Map<String, String> requestBody){
        var storageName = requestBody.get("name");
        this.storageRepository.save(new Storage(storageName));
    }
}
