package com.shizubro.cardcollection.service;

import com.shizubro.cardcollection.model.ScryfallCard;
import com.shizubro.cardcollection.model.User;
import com.shizubro.cardcollection.model.UserCardEntry;
import com.shizubro.cardcollection.repository.ScryfallCardRepository;
import com.shizubro.cardcollection.repository.UserCardEntryRepository;
import com.shizubro.cardcollection.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class CardBorrowerService {
    private final UserCardEntryRepository userCardEntryRepository;
    private final UserRepository userRepository;
    private final ScryfallCardRepository scryfallCardRepository;

    @Autowired
    public CardBorrowerService(UserCardEntryRepository userCardEntryRepository, UserRepository userRepository, ScryfallCardRepository scryfallCardRepository) {
        this.userCardEntryRepository = userCardEntryRepository;
        this.userRepository = userRepository;
        this.scryfallCardRepository = scryfallCardRepository;
    }

    public UserCardEntry lendCardFromOwnerToUser(UUID ownerId, UUID borrowerId, UUID scryfallCardId, Long count) {
        UserCardEntry existingCardEntry = this.userCardEntryRepository.findUserCardEntryByOwnerIdAndBorrowerIdAndCardId(ownerId, borrowerId, scryfallCardId);
        if (existingCardEntry != null) {
            existingCardEntry.setCount(existingCardEntry.getCount() + count);
            return this.userCardEntryRepository.save(existingCardEntry);
        } else {
            UserCardEntry newCardEntry = new UserCardEntry();
            Optional<ScryfallCard> cardToLend = this.scryfallCardRepository.findById(scryfallCardId);
            Optional<User> borrowerUser = this.userRepository.findById(borrowerId);
            Optional<User> ownerUser = this.userRepository.findById(ownerId);

            newCardEntry.setCard(cardToLend.orElseThrow());
            newCardEntry.setBorrower(borrowerUser.orElseThrow());
            newCardEntry.setOwner(ownerUser.orElseThrow());
            newCardEntry.setCount(count);

            return this.userCardEntryRepository.save(newCardEntry);
        }
    }
//
//    public void returnCardFromUserToOwner(UUID borrower_id, UUID owner_id, UUID scryfallCardId, Long count) {
//
//    }
//
//    public void lendCardFromNonOwnerToUser(UUID owner_id, UUID lender_id, UUID borrower_id, UUID scryfallCardId, Long count) {
//
//    }
//
//    public List<UserCardEntryDto> getLentOutCardsForOwner(UUID owner_id) {
//
//    }
//
//    public List<UserCardEntryDto> getCardsBorrowedByUser(UUID borrower_id) {
//
//    }
}
