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
    @Query("SELECT c FROM ScryfallCard  c WHERE (:name is null or c.name = :name) and (:setcode is null"
            + " or c.setcode = :setcode) and (:cnumber is null or c.collectorNumber = :cnumber)")
    List<ScryfallCard> findScryfallCardByCardFilters(@Param("name") String name, @Param("setcode") String setcode, @Param("cnumber") String cnumber);
}