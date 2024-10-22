package com.shizubro.cardcollection.service;

import com.shizubro.cardcollection.dto.UserCardEntryDto;
import com.shizubro.cardcollection.repository.UserCardEntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CardBorrowerService {
    private final UserCardEntryRepository userCardEntryRepository;

    @Autowired
    public CardBorrowerService(UserCardEntryRepository userCardEntryRepository) {
        this.userCardEntryRepository = userCardEntryRepository;
    }

//    public void lendCardFromOwnerToUser(String owner_id, String borrower_id, String scryfallCardId, Long count) {
//
//    }
//
//    public void returnCardFromUserToOwner(String borrower_id, String owner_id, String scryfallCardId, Long count) {
//
//    }
//
//    public void lendCardFromNonOwnerToUser(String owner_id, String lender_id, String borrower_id, String scryfallCardId, Long count) {
//
//    }
//
//    public List<UserCardEntryDto> getLentOutCardsForOwner(String owner_id) {
//
//    }
//
//    public List<UserCardEntryDto> getCardsBorrowedByUser(String borrower_id) {
//
//    }
}
