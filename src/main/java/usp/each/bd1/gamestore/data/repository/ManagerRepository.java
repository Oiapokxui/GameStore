package usp.each.bd1.gamestore.data.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import usp.each.bd1.gamestore.data.entity.Item;
import usp.each.bd1.gamestore.data.entity.Manager;

@Repository
public interface ManagerRepository extends CrudRepository<Manager, String> {
    @Query(value="select * from gerente", nativeQuery = true)
    List<Manager> findAll();
}
