package usp.each.bd1.gamestore.data.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import usp.each.bd1.gamestore.data.entity.Purchase;
import usp.each.bd1.gamestore.data.entity.PurchaseId;

@Repository
public interface PurchaseRepository extends CrudRepository<Purchase, PurchaseId> {
    @Query(value="select * from compra", nativeQuery = true)
    List<Purchase> findAll();

    @Transactional
    @Modifying
    @Query(value = "delete from compra cmpr where cmpr.cpf_gerente=:cpf", nativeQuery = true)
    void deleteByManager(@Param("cpf") String id);

    @Modifying
    @Transactional
    @Query(value = "delete from compra prod where prod.codigo_de_barras in :barcodes", nativeQuery = true)
    void deleteIfInList(@Param("barcodes") List<String> sales);
}
