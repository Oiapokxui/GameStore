package usp.each.bd1.gamestore.data.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import usp.each.bd1.gamestore.data.entity.Person;

@Repository
public interface PersonRepository extends CrudRepository<Person, String> {
    @Query(value="select * from pessoa", nativeQuery = true)
    List<Person> findAll();
}
