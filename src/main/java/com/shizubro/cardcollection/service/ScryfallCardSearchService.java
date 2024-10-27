package com.shizubro.cardcollection.service;

import com.shizubro.cardcollection.model.ScryfallCard;
import com.shizubro.cardcollection.repository.ScryfallCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScryfallCardSearchService {
    private final ScryfallCardRepository scryfallCardRepository;

    @Autowired
    public ScryfallCardSearchService(ScryfallCardRepository scryfallCardRepository) {
        this.scryfallCardRepository = scryfallCardRepository;
    }

    public List<ScryfallCard> searchScryfallCardByNameSetNumber(String name, String setcode, String collectorNumber) {
        return this.scryfallCardRepository.findScryfallCardByCardFilters(name, setcode, collectorNumber);
    }
}
