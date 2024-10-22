package com.shizubro.cardcollection.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity()
@Table(name = "user_card_entries")
@Getter
@Setter
public class UserCardEntry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne()
    @JoinColumn(name= "card_id")
    private ScryfallCard card;

    @ManyToOne()
    @JoinColumn(name= "owner_id")
    private User owner;

    @ManyToOne()
    @JoinColumn(name= "borrower_id")
    private User borrower;

    @Column()
    private Long count;
}
