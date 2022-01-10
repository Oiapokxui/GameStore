package usp.each.bd1.gamestore.data.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import usp.each.bd1.gamestore.data.entity.Assistance;
import usp.each.bd1.gamestore.data.entity.AssistanceId;
import usp.each.bd1.gamestore.data.entity.RepairItem;

@Repository
public interface RepairItemRepository extends CrudRepository<RepairItem, Integer> {
    @Transactional
    @Modifying
    @Query(value = "delete from item_a_reparar where cpf_dono=:cpf", nativeQuery = true)
    void deleteWhereCustomer(@Param("cpf") String id);

    @Query(value = "select * from item_a_reparar where cpf_dono=:cpf", nativeQuery = true)
    List<RepairItem> selectByCustomer(@Param("cpf") String id);

}
