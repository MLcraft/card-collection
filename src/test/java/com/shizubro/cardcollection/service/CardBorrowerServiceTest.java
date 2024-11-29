package com.shizubro.cardcollection.service;

import com.shizubro.cardcollection.model.ScryfallCard;
import com.shizubro.cardcollection.model.User;
import com.shizubro.cardcollection.model.UserCardEntry;
import com.shizubro.cardcollection.repository.ScryfallCardRepository;
import com.shizubro.cardcollection.repository.UserCardEntryRepository;
import com.shizubro.cardcollection.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;
import java.util.UUID;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class CardBorrowerServiceTest {

    @Mock
    private UserCardEntryRepository userCardEntryRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private ScryfallCardRepository scryfallCardRepository;

    @InjectMocks
    private CardBorrowerService cardBorrowerService;

    private UUID ownerId;
    private UUID borrowerId;
    private UUID scryfallCardId;
    private UserCardEntry cardEntry;
    private ScryfallCard scryfallCard;
    private User owner;
    private User borrower;



    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        ownerId = UUID.randomUUID();
        borrowerId = UUID.randomUUID();
        scryfallCardId = UUID.randomUUID();
        cardEntry = new UserCardEntry();
        scryfallCard = new ScryfallCard();
        owner = new User();
        borrower = new User();
        cardEntry.setCard(scryfallCard);
        cardEntry.setOwner(owner);
        cardEntry.setBorrower(borrower);
        cardEntry.setCount(5L);
    }

    @Test
    void testLendCardFromOwnerToUser_whenCardEntryExists() {
        when(userCardEntryRepository.findUserCardEntryByOwnerIdAndBorrowerIdAndCardId(ownerId, borrowerId, scryfallCardId))
                .thenReturn(cardEntry);

        cardBorrowerService.lendCardFromOwnerToUser(ownerId, borrowerId, scryfallCardId, 2L);

        verify(userCardEntryRepository, times(1)).save(cardEntry);
        assertEquals(7L, cardEntry.getCount());
    }

    @Test
    void testLendCardFromOwnerToUser_whenCardEntryDoesNotExist() {
        when(userCardEntryRepository.findUserCardEntryByOwnerIdAndBorrowerIdAndCardId(ownerId, borrowerId, scryfallCardId))
                .thenReturn(null);
        when(scryfallCardRepository.findById(scryfallCardId)).thenReturn(Optional.of(scryfallCard));
        when(userRepository.findById(borrowerId)).thenReturn(Optional.of(borrower));
        when(userRepository.findById(ownerId)).thenReturn(Optional.of(owner));

        cardBorrowerService.lendCardFromOwnerToUser(ownerId, borrowerId, scryfallCardId, 3L);

        verify(userCardEntryRepository, times(1)).save(any(UserCardEntry.class));
    }

    @Test
    void testReturnCardFromUserToOwner_whenCardEntryExistsAndCountIsReduced() {
        when(userCardEntryRepository.findUserCardEntryByOwnerIdAndBorrowerIdAndCardId(ownerId, borrowerId, scryfallCardId))
                .thenReturn(cardEntry);

        cardBorrowerService.returnCardFromUserToOwner(ownerId, borrowerId, scryfallCardId, 3L);

        verify(userCardEntryRepository, times(1)).save(cardEntry);
        assertEquals(2L, cardEntry.getCount());
    }

    @Test
    void testReturnCardFromUserToOwner_whenCardEntryExistsAndIsDeleted() {
        when(userCardEntryRepository.findUserCardEntryByOwnerIdAndBorrowerIdAndCardId(ownerId, borrowerId, scryfallCardId))
                .thenReturn(cardEntry);

        cardBorrowerService.returnCardFromUserToOwner(ownerId, borrowerId, scryfallCardId, 5L);

        verify(userCardEntryRepository, times(1)).delete(cardEntry);
    }

    @Test
    void testGetLentOutCardsForOwner() {
        List<UserCardEntry> cardEntries = Arrays.asList(cardEntry);
        when(userCardEntryRepository.findAllByOwnerId(ownerId)).thenReturn(cardEntries);

        List<UserCardEntry> result = cardBorrowerService.getLentOutCardsForOwner(ownerId);

        assertEquals(1, result.size());
        verify(userCardEntryRepository, times(1)).findAllByOwnerId(ownerId);
    }

    @Test
    void testGetCardsBorrowedByUser() {
        List<UserCardEntry> cardEntries = Arrays.asList(cardEntry);
        when(userCardEntryRepository.findAllByBorrowerId(borrowerId)).thenReturn(cardEntries);

        List<UserCardEntry> result = cardBorrowerService.getCardsBorrowedByUser(borrowerId);

        assertEquals(1, result.size());
        verify(userCardEntryRepository, times(1)).findAllByBorrowerId(borrowerId);
    }
}
