package com.shizubro.cardcollection.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity()
@Table(name = "scryfall_cards")
@Getter
@Setter
public class ScryfallCard {
    @Id()
    private UUID id;

    @Column(nullable = false)
    private String name;

}
