package usp.each.bd1.gamestore.data.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import usp.each.bd1.gamestore.data.entity.Storage;

@Repository
public interface StorageRepository extends CrudRepository<Storage, String> {
    @Query(value="select * from ESTOQUE", nativeQuery = true)
    List<Storage> findAll();

    @Transactional
    @Modifying
    @Query(value="delete from estoque where nome=:name", nativeQuery = true)
    void deleteById(@Param("name") String storageName);
}
