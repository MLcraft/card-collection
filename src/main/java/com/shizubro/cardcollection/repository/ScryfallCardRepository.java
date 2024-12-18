package com.shizubro.cardcollection.repository;

import com.shizubro.cardcollection.model.ScryfallCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ScryfallCardRepository extends JpaRepository<ScryfallCard, UUID> {
    @Query("SELECT c FROM ScryfallCard  c WHERE (LOWER(c.name) = LOWER(:name) or :name is null) and (LOWER(c.setcode) = LOWER(:setcode)"
            + " or :setcode is null) and (LOWER(c.collectorNumber) = LOWER(:cnumber) or :cnumber is null)")
    List<ScryfallCard> findScryfallCardByCardFilters(@Param("name") String name, @Param("setcode") String setcode, @Param("cnumber") String cnumber);
}