package usp.each.bd1.gamestore.data.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import usp.each.bd1.gamestore.data.entity.Manager;

@Repository
public interface ManagerRepository extends CrudRepository<Manager, String> {
    @Override
    @Query(value="select * from gerente", nativeQuery = true)
    List<Manager> findAll();

    @Query(value = "select * from gerente ger where ger.cpf=:cpf", nativeQuery = true)
    Optional<Manager> findByCpf(@Param("cpf") String cpf);
}
