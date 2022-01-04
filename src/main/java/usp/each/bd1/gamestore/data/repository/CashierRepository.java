package usp.each.bd1.gamestore.data.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import usp.each.bd1.gamestore.data.entity.Cashier;

@Repository
public interface CashierRepository extends CrudRepository<Cashier, String> {
    @Override
    @Query(value="select * from caixa", nativeQuery = true)
    List<Cashier> findAll();

    @Query(value = "select * from caixa cx where cx.cpf=:cpf", nativeQuery = true)
    Optional<Cashier> findByCpf(@Param("cpf") String cpf);
}
