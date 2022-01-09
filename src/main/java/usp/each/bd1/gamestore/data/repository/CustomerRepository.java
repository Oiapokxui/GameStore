package usp.each.bd1.gamestore.data.repository;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import usp.each.bd1.gamestore.data.entity.Customer;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, String> {
    @Query(value="select * from CLIENTE", nativeQuery = true)
    List<Customer> findAll();

    @Query(value = "select * from CLIENTE where cpf=:cpf", nativeQuery = true)
    Optional<Customer> findById(@Param("cpf") String id);

    @Transactional
    @Modifying
    @Query(value = "delete from CLIENTE where cpf=:cpf", nativeQuery = true)
    void deleteById(@Param("cpf") String id);
}