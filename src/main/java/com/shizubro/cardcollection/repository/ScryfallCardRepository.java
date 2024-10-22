package com.shizubro.cardcollection.repository;

import com.shizubro.cardcollection.model.ScryfallCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ScryfallCardRepository extends JpaRepository<ScryfallCard, UUID> {
}