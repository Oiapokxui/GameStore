package usp.each.bd1.gamestore.data.repository;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import usp.each.bd1.gamestore.data.entity.Technician;

@Repository
public interface TechnicianRepository extends CrudRepository<Technician, String> {
    @Override
    @Query(value="select * from caixa", nativeQuery = true)
    List<Technician> findAll();

    @Query(value = "select * from caixa cx where cx.cpf=:cpf", nativeQuery = true)
    Optional<Technician> findByCpf(@Param("cpf") String cpf);

    @Transactional
    @Modifying
    @Query(value = "delete from tecnico_de_manutencao where cpf=:cpf", nativeQuery = true)
    void deleteById(@Param("cpf") String id);

    @Transactional
    @Modifying
    @Query(value = "insert into funcionario values (:cpf)", nativeQuery=true)
    void insert(@Param("cpf") String cpf);
}
