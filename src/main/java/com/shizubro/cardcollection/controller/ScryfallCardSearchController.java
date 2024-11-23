package com.shizubro.cardcollection.controller;

import com.shizubro.cardcollection.dto.requests.SearchScryfallCardsRequestDto;
import com.shizubro.cardcollection.dto.responses.ScryfallCardResponseDto;
import com.shizubro.cardcollection.dto.responses.UserCardEntryResponseDto;
import com.shizubro.cardcollection.mapper.ScryfallCardMapper;
import com.shizubro.cardcollection.model.ScryfallCard;
import com.shizubro.cardcollection.service.ScryfallCardSearchService;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = ScryfallCardResponseDto.class))) }),
            @ApiResponse(responseCode = "500", description = "Error occured while searching cards on scryfall API",
                    content = @Content) })
    @Tag(name = "POST scryfall API card search", description = "POST methods to search external Scryfall card data APIs")
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
