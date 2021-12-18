package usp.each.bd1.gamestore.data.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import usp.each.bd1.gamestore.data.entity.Employee;
import usp.each.bd1.gamestore.data.entity.Item;
import usp.each.bd1.gamestore.data.entity.ItemSupplierTelephone;
import usp.each.bd1.gamestore.data.entity.Repair;
import usp.each.bd1.gamestore.data.entity.RepairId;

@Repository
public interface ItemRepository extends CrudRepository<Item, String> {
}
