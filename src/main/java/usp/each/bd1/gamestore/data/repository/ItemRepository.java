package usp.each.bd1.gamestore.data.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import usp.each.bd1.gamestore.data.entity.Item;
import usp.each.bd1.gamestore.data.entity.Storage;

@Repository
public interface ItemRepository extends CrudRepository<Item, String> {
    @Query(value="select * from PRODUTO", nativeQuery = true)
    List<Item> findAll();

    @Query(value = "select * from produto prod where prod.nome_estoque=:nome", nativeQuery = true)
    List<Item> findByStorageName(@Param("nome") String nome);

    @Query(value = "select * from produto prod where prod.nome=:nome", nativeQuery = true)
    List<Item> searchByName(@Param("nome") String itemName);

    List<Item> findItemByStorageEquals(Storage hey);
}
