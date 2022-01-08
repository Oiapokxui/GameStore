package usp.each.bd1.gamestore.data.repository;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import usp.each.bd1.gamestore.data.entity.Assistance;
import usp.each.bd1.gamestore.data.entity.AssistanceId;
import usp.each.bd1.gamestore.data.entity.Sale;

@Repository
public interface AssistanceRepository extends CrudRepository<Assistance, AssistanceId> {
    @Query(value="select * from atende", nativeQuery = true)
    List<Assistance> findAll();

    @Transactional
    @Modifying
    @Query(value = "delete from atende atend where atend.cpf_atendente=:cpf", nativeQuery = true)
    void deleteAssistances(@Param("cpf") String id);
}
