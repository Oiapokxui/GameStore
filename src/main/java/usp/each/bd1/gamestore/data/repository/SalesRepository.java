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

@Repository
public interface SalesRepository extends CrudRepository<Sale, BigInteger> {
    @Query(value="select * from venda ", nativeQuery = true)
    List<Sale> findAll();

    @Query(value = "select * from atendente where cpf=:cpf", nativeQuery = true)
    Optional<Sale> findById(@Param("cpf") String id);

    @Transactional
    @Modifying
    @Query(value = "update venda set cpf_caixa = null where cpf_caixa=:cpf", nativeQuery = true)
    void unassignCashier(@Param("cpf") String id);
}
