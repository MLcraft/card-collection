package com.shizubro.cardcollection.controller;

import com.shizubro.cardcollection.dto.requests.SearchScryfallCardsRequestDto;
import com.shizubro.cardcollection.dto.responses.ScryfallCardResponseDto;
import com.shizubro.cardcollection.mapper.ScryfallCardMapper;
import com.shizubro.cardcollection.model.ScryfallCard;
import com.shizubro.cardcollection.service.ScryfallCardSearchService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController()
@RequestMapping("/scryfall")
public class ScryfallCardSearchController {
    private final ScryfallCardSearchService scryfallCardSearchService;
    private final ScryfallCardMapper scryfallCardMapper;

    @Autowired
    public ScryfallCardSearchController(ScryfallCardSearchService scryfallCardSearchService, ScryfallCardMapper scryfallCardMapper) {
        this.scryfallCardSearchService = scryfallCardSearchService;
        this.scryfallCardMapper = scryfallCardMapper;
    }

    @PostMapping("/search")
    public List<ScryfallCardResponseDto> searchScryfallCards(@RequestBody SearchScryfallCardsRequestDto requestDto) {
        try {
            List<ScryfallCard> searchResults = this.scryfallCardSearchService.searchScryfallCardByNameSetNumber(requestDto.getName(), requestDto.getSetCode(), requestDto.getCollectorNumber());
            List<ScryfallCardResponseDto> scryfallCardResponseDtos = new ArrayList<>();
            searchResults.forEach(u -> scryfallCardResponseDtos.add(this.scryfallCardMapper.entityToResponseDto(u)));
            return scryfallCardResponseDtos;

        } catch (Exception e) {
            log.error("Exception occurred in searchScryfallCards");
            log.error(e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "An error occured while processing the request\n");
        }
    }
}
