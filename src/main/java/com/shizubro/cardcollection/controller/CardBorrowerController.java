package com.shizubro.cardcollection.controller;

import com.shizubro.cardcollection.dto.UserCardEntryDto;
import com.shizubro.cardcollection.service.CardBorrowerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Slf4j
@RestController()
public class CardBorrowerController {
    private final CardBorrowerService cardBorrowerService;

    @Autowired
    public CardBorrowerController(CardBorrowerService cardBorrowerService) {
        this.cardBorrowerService = cardBorrowerService;
    }

    @PostMapping("/lend")
    public String lendCardsFromOwnerToUser() {
//        try {
//            this.cardBorrowerService.lendCardFromOwnerToUser();
//        } catch (Exception e) {
//            log.error("Exception occurred in lendCardsFromOwnerToUser");
//            log.error(e.getMessage());
//            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "An error occured while processing the request\n");
//        }
        return "lendCardsFromOwnerToUser";
    }
//
//    @PostMapping("/return")
//    public String returnCardsToOwner() {
//        try {
//            this.cardBorrowerService.returnCardFromUserToOwner();
//        } catch (Exception e) {
//            log.error("Exception occurred in returnCardFromUserToOwner");
//            log.error(e.getMessage());
//            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "An error occured while processing the request\n");
//        }
//    }
//
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
//
//    @GetMapping("/lentCards")
//    public List<UserCardEntryDto> getCardsLentByOwner() {
//        try {
//            this.cardBorrowerService.getLentOutCardsForOwner();
//        } catch (Exception e) {
//            log.error("Exception occurred in getLentOutCardsForOwner");
//            log.error(e.getMessage());
//            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "An error occured while processing the request\n");
//        }
//    }
//
//    @GetMapping("/borrowedCards")
//    public List<UserCardEntryDto> getCardsBorrowedByUser() {
//        try {
//            this.cardBorrowerService.getCardsBorrowedByUser();
//        } catch (Exception e) {
//            log.error("Exception occurred in getCardsBorrowedByUser");
//            log.error(e.getMessage());
//            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "An error occured while processing the request\n");
//        }
//    }

    @RequestMapping("/")
    public String index() {
        return "hello world";
    }
}
