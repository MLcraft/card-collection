package com.shizubro.cardcollection.repository;

import com.shizubro.cardcollection.model.ScryfallCard;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ScryfallCardRepository extends JpaRepository<ScryfallCard, UUID> {
}