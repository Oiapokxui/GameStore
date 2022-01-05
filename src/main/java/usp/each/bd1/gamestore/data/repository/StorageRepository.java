package usp.each.bd1.gamestore.data.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import usp.each.bd1.gamestore.data.entity.Storage;

@Repository
public interface StorageRepository extends CrudRepository<Storage, String> {
    @Query(value="select * from ESTOQUE", nativeQuery = true)
    List<Storage> findAll();
}
