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

import usp.each.bd1.gamestore.data.entity.Sale;
import usp.each.bd1.gamestore.data.entity.SalesAssociate;

@Repository
public interface SalesAssociateRepository extends CrudRepository<SalesAssociate, String> {
    @Query(value="select * from atendente ", nativeQuery = true)
    List<SalesAssociate> findAll();

    @Query(value = "select * from atendente where cpf=:cpf", nativeQuery = true)
    Optional<SalesAssociate> findById(@Param("cpf") String id);

    @Transactional
    @Modifying
    @Query(value = "delete from atendente where cpf=:cpf", nativeQuery = true)
    void deleteById(@Param("cpf") String id);

    @Transactional
    @Modifying
    @Query(value = "insert into funcionario values (:cpf)", nativeQuery=true)
    void insert(@Param("cpf") String cpf);
}
