package com.shizubro.cardcollection.mapper;

import com.shizubro.cardcollection.dto.responses.UserCardEntryResponseDto;
import com.shizubro.cardcollection.model.ScryfallCard;
import com.shizubro.cardcollection.model.User;
import com.shizubro.cardcollection.model.UserCardEntry;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class UserCardEntryMapperTest {

    private UserCardEntryMapper userCardEntryMapper;
    private UserCardEntry userCardEntry;
    private ScryfallCard card;
    private User owner;
    private User borrower;

    @BeforeEach
    void setUp() {
        // Initialize the mapper
        userCardEntryMapper = new UserCardEntryMapper();

        card = new ScryfallCard();
        card.setName("Test Card");
        card.setSetcode("TST");
        card.setCollectorNumber("001");
        card.setImageUri("https://example.com/card.jpg");

        owner = new User();
        owner.setDiscordId(123456789L);

        borrower = new User();
        borrower.setDiscordId(987654321L);

        userCardEntry = new UserCardEntry();
        userCardEntry.setId(1L);  // Sample ID
        userCardEntry.setCard(card);
        userCardEntry.setOwner(owner);
        userCardEntry.setBorrower(borrower);
        userCardEntry.setCount(3L);  // Example count
    }

    @Test
    void testEntityToResponseDto() {
        UserCardEntryResponseDto responseDto = userCardEntryMapper.entityToResponseDto(userCardEntry);

        assertNotNull(responseDto);
        assertEquals(userCardEntry.getId(), responseDto.getId());
        assertEquals(userCardEntry.getCard().getName(), responseDto.getCardName());
        assertEquals(userCardEntry.getCard().getSetcode(), responseDto.getCardSetCode());
        assertEquals(userCardEntry.getCard().getCollectorNumber(), responseDto.getCardNumber());
        assertEquals(userCardEntry.getCard().getImageUri(), responseDto.getCardImageUrl());
        assertEquals(userCardEntry.getOwner().getDiscordId().toString(), responseDto.getOwnerId());
        assertEquals(userCardEntry.getBorrower().getDiscordId().toString(), responseDto.getBorrowerId());
        assertEquals(userCardEntry.getCount(), responseDto.getCount());
    }
}

