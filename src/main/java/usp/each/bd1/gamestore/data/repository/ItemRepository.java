package usp.each.bd1.gamestore.data.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import usp.each.bd1.gamestore.data.entity.Item;

@Repository
public interface ItemRepository extends CrudRepository<Item, String> {
    @Query(value="select * from PRODUTO", nativeQuery = true)
    List<Item> findAll();
}
