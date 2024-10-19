package com.shizubro.cardcollection.controller;

import com.shizubro.cardcollection.model.UserCardEntry;
import com.shizubro.cardcollection.repository.UserCardEntryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController()
public class CardCollectionController {
    private final UserCardEntryRepository userCardEntryRepository;

    @Autowired
    public CardCollectionController(UserCardEntryRepository userCardEntryRepository) {

        this.userCardEntryRepository = userCardEntryRepository;
    }

    @RequestMapping("/testDB")
    public List<UserCardEntry> testDB() {
        return this.userCardEntryRepository.findAll();
    }

    @RequestMapping("/")
    public String index() {
//        UserCardEntry testUserCardEntry = new UserCardEntry();
//        testUserCardEntry.setId(1L);
//        testUserCardEntry.setName("Shizubro");
//        log.info(testUserCardEntry.getName());
//        return this.cardRepository.save(testUserCardEntry);
        return "hello world";
    }
}
