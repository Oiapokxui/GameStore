package usp.each.bd1.gamestore.data.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import usp.each.bd1.gamestore.data.entity.Item;
import usp.each.bd1.gamestore.data.entity.Purchase;
import usp.each.bd1.gamestore.data.entity.PurchaseId;

@Repository
public interface PurchaseRepository extends CrudRepository<Purchase, PurchaseId> {
}
