package com.shizubro.cardcollection.service;

import com.shizubro.cardcollection.dto.UserCardEntryDto;
import com.shizubro.cardcollection.mapper.UserCardEntryMapper;
import com.shizubro.cardcollection.model.ScryfallCard;
import com.shizubro.cardcollection.model.User;
import com.shizubro.cardcollection.model.UserCardEntry;
import com.shizubro.cardcollection.repository.ScryfallCardRepository;
import com.shizubro.cardcollection.repository.UserCardEntryRepository;
import com.shizubro.cardcollection.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
public class CardBorrowerService {
    private final UserCardEntryRepository userCardEntryRepository;
    private final UserCardEntryMapper userCardEntryMapper;
    private final UserRepository userRepository;
    private final ScryfallCardRepository scryfallCardRepository;

    @Autowired
    public CardBorrowerService(UserCardEntryRepository userCardEntryRepository, UserCardEntryMapper userCardEntryMapper, UserRepository userRepository, ScryfallCardRepository scryfallCardRepository) {
        this.userCardEntryRepository = userCardEntryRepository;
        this.userCardEntryMapper = userCardEntryMapper;
        this.userRepository = userRepository;
        this.scryfallCardRepository = scryfallCardRepository;
    }

    public void lendCardFromOwnerToUser(UUID ownerId, UUID borrowerId, UUID scryfallCardId, Long count) {
        UserCardEntry cardEntry = this.userCardEntryRepository.findUserCardEntryByOwnerIdAndBorrowerIdAndCardId(ownerId, borrowerId, scryfallCardId);
        if (cardEntry != null) {
            cardEntry.setCount(cardEntry.getCount() + count);
        } else {
            cardEntry = new UserCardEntry();
            Optional<ScryfallCard> cardToLend = this.scryfallCardRepository.findById(scryfallCardId);
            Optional<User> borrowerUser = this.userRepository.findById(borrowerId);
            Optional<User> ownerUser = this.userRepository.findById(ownerId);

            cardEntry.setCard(cardToLend.orElseThrow());
            cardEntry.setBorrower(borrowerUser.orElseThrow());
            cardEntry.setOwner(ownerUser.orElseThrow());
            cardEntry.setCount(count);
        }
        this.userCardEntryRepository.save(cardEntry);
    }

    public void returnCardFromUserToOwner(UUID ownerId, UUID borrowerId, UUID scryfallCardId, Long count) {
        UserCardEntry existingCardEntry = this.userCardEntryRepository.findUserCardEntryByOwnerIdAndBorrowerIdAndCardId(ownerId, borrowerId, scryfallCardId);
        if (existingCardEntry != null) {
            Long remainingCount = existingCardEntry.getCount() - count;
            if (remainingCount <= 0L) {
                this.userCardEntryRepository.delete(existingCardEntry);
            } else {
                existingCardEntry.setCount(remainingCount);
                this.userCardEntryRepository.save(existingCardEntry);
            }
        }
    }

//    public void lendCardFromNonOwnerToUser(UUID ownerId, UUID lenderId, UUID borrowerId, UUID scryfallCardId, Long count) {
//        this.returnCardFromUserToOwner(ownerId, lenderId, scryfallCardId, count);
//        this.lendCardFromOwnerToUser(ownerId, borrowerId, scryfallCardId, count);
//    }

    public List<UserCardEntryDto> getLentOutCardsForOwner(UUID ownerId) {
        List<UserCardEntry> userCardEntries = this.userCardEntryRepository.findAllByOwnerId(ownerId);
        List<UserCardEntryDto> userCardEntryDtos = new ArrayList<>();
        userCardEntries.forEach(u -> userCardEntryDtos.add(this.userCardEntryMapper.entityToDto(u)));

        return userCardEntryDtos;
    }

    public List<UserCardEntryDto> getCardsBorrowedByUser(UUID borrowerId) {
        List<UserCardEntry> userCardEntries = this.userCardEntryRepository.findAllByBorrowerId(borrowerId);
        List<UserCardEntryDto> userCardEntryDtos = new ArrayList<>();
        userCardEntries.forEach(u -> userCardEntryDtos.add(this.userCardEntryMapper.entityToDto(u)));

        return userCardEntryDtos;
    }
}
