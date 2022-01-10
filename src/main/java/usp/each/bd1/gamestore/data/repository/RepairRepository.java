package usp.each.bd1.gamestore.data.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import usp.each.bd1.gamestore.data.entity.Repair;
import usp.each.bd1.gamestore.data.entity.RepairId;
import usp.each.bd1.gamestore.data.entity.RepairItem;

@Repository
public interface RepairRepository extends CrudRepository<Repair, RepairId> {
    @Query(value="select * from conserta", nativeQuery = true)
    List<Repair> findAll();

    @Transactional
    @Modifying
    @Query(value = "delete from conserta where conserta.cpf_tecnico=:cpf", nativeQuery = true)
    void deleteRepairsByTechnician(@Param("cpf") String id);

    @Transactional
    @Modifying
    @Query(value = "delete from conserta where conserta.id_item_reparo in :items", nativeQuery = true)
    void deleteRepairsByRepairItem(@Param("items") List<Integer> items);
}
