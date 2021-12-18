package usp.each.bd1.gamestore.data.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import usp.each.bd1.gamestore.data.entity.Person;

@Repository
public interface PersonRepository extends CrudRepository<Person, String> {
    @Query(value="select * from pessoa", nativeQuery = true)
    List<Person> findAll();

    @Transactional
    @Modifying
    @Query(value = "insert into pessoa values (:cpf, :nome)", nativeQuery = true)
    void insert(@Param("cpf") String cpf, @Param("nome") String name);

}
