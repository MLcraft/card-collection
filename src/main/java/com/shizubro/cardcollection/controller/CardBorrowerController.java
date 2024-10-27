package com.shizubro.cardcollection.controller;

import com.shizubro.cardcollection.dto.responses.UserCardEntryResponseDto;
import com.shizubro.cardcollection.dto.requests.LendCardsRequestDto;
import com.shizubro.cardcollection.mapper.UserCardEntryMapper;
import com.shizubro.cardcollection.model.UserCardEntry;
import com.shizubro.cardcollection.service.CardBorrowerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Slf4j
@RestController()
@RequestMapping("/lending")
public class CardBorrowerController {
    private final CardBorrowerService cardBorrowerService;
    private final UserCardEntryMapper userCardEntryMapper;

    @Autowired
    public CardBorrowerController(CardBorrowerService cardBorrowerService, UserCardEntryMapper userCardEntryMapper) {
        this.cardBorrowerService = cardBorrowerService;
        this.userCardEntryMapper = userCardEntryMapper;
    }

    @PostMapping("/lend")
    public String lendCardsFromOwnerToUser(@RequestBody LendCardsRequestDto requestDto) {
        try {
            this.cardBorrowerService.lendCardFromOwnerToUser(UUID.fromString(requestDto.getOwnerId()), UUID.fromString(requestDto.getBorrowerId()), UUID.fromString(requestDto.getCardId()), requestDto.getCount());
        } catch (Exception e) {
            log.error("Exception occurred in lendCardsFromOwnerToUser");
            log.error(e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "An error occured while processing the request\n");
        }
        return "lendCardsFromOwnerToUser";
    }

    @PostMapping("/return")
    public String returnCardsFromUserToOwner(@RequestBody LendCardsRequestDto requestDto) {
        try {
            this.cardBorrowerService.returnCardFromUserToOwner(UUID.fromString(requestDto.getOwnerId()), UUID.fromString(requestDto.getBorrowerId()), UUID.fromString(requestDto.getCardId()), requestDto.getCount());
        } catch (Exception e) {
            log.error("Exception occurred in returnCardsFromUserToOwner");
            log.error(e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "An error occured while processing the request\n");
        }
        return "returnCardsFromUserToOwner";
    }

//    @PostMapping("/transfer")
//    public String lendCardsFromBorrowerToOtherUser() {
//        try {
//            this.cardBorrowerService.lendCardFromNonOwnerToUser();
//        } catch (Exception e) {
//            log.error("Exception occurred in lendCardFromNonOwnerToUser");
//            log.error(e.getMessage());
//            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "An error occured while processing the request\n");
//        }
//    }

    @GetMapping("/lentCards/{ownerId}")
    public List<UserCardEntryResponseDto> getCardsLentByOwner(@PathVariable String ownerId) {
        try {
            List<UserCardEntry> userCardEntries = this.cardBorrowerService.getLentOutCardsForOwner(UUID.fromString(ownerId));
            List<UserCardEntryResponseDto> userCardEntryDtos = new ArrayList<>();
            userCardEntries.forEach(u -> userCardEntryDtos.add(this.userCardEntryMapper.entityToResponseDto(u)));
            return userCardEntryDtos;
        } catch (Exception e) {
            log.error("Exception occurred in getLentOutCardsForOwner");
            log.error(e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "An error occured while processing the request\n");
        }
    }

    @GetMapping("/borrowedCards/{borrowerId}")
    public List<UserCardEntryResponseDto> getCardsBorrowedByUser(@PathVariable String borrowerId) {
        try {
            List<UserCardEntry> userCardEntries = this.cardBorrowerService.getCardsBorrowedByUser(UUID.fromString(borrowerId));
            List<UserCardEntryResponseDto> userCardEntryDtos = new ArrayList<>();
            userCardEntries.forEach(u -> userCardEntryDtos.add(this.userCardEntryMapper.entityToResponseDto(u)));
            return userCardEntryDtos;
        } catch (Exception e) {
            log.error("Exception occurred in getCardsBorrowedByUser");
            log.error(e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "An error occured while processing the request\n");
        }
    }

    @RequestMapping("/")
    public String index() {
        return "hello world";
    }
}
