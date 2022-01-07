package usp.each.bd1.gamestore.data.repository;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import usp.each.bd1.gamestore.data.entity.Item;
import usp.each.bd1.gamestore.data.entity.Sale;
import usp.each.bd1.gamestore.data.entity.Storage;

@Repository
public interface ItemRepository extends CrudRepository<Item, String> {
    @Query(value="select * from PRODUTO", nativeQuery = true)
    List<Item> findAll();

    @Query(value = "select * from produto prod where prod.nome_estoque=:nome", nativeQuery = true)
    List<Item> findByStorageName(@Param("nome") String nome);

    @Query(value = "select * from produto prod where prod.nome=:nome", nativeQuery = true)
    List<Item> findByName(@Param("nome") String itemName);

    @Query(value = "select * from produto prod where prod.codigo_de_barras=:barcode", nativeQuery = true)
    Optional<Item> findById(@Param("barcode") String itemName);

    @Query(value = "select estoq.nome from produto prod inner join estoque estoq on estoq.nome = prod.nome_estoque where prod.codigo_de_barras=:barcode", nativeQuery = true)
    Optional<Storage> getStorageByBarcode(@Param("barcode") String barcode);

    @Query(value = "select vnd.id from produto prod inner join venda vnd on vnd.id = prod.id_venda where prod.codigo_de_barras=:barcode", nativeQuery = true)
    Optional<Sale> getSaleByBarcode(@Param("barcode") String barcode);

    @Modifying
    @Transactional
    @Query(value = "delete from produto prod where prod.codigo_de_barras=:barcode", nativeQuery = true)
    void deleteById(@Param("barcode") String barcode);

    List<Item> findItemByStorageEquals(Storage hey);
}
