package usp.each.bd1.gamestore.data.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import usp.each.bd1.gamestore.data.entity.Accessory;
import usp.each.bd1.gamestore.data.entity.VideoGame;

@Repository
public interface VideoGameRepository extends CrudRepository<VideoGame, String> {

    @Modifying
    @Transactional
    @Query(value = "delete from jogos_midia_fisica prod where prod.codigo_de_barras in :barcodes", nativeQuery = true)
    void deleteIfInList(@Param("barcodes") List<String> sales);
}
